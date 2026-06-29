import sys
import subprocess
import ctypes
from ctypes import wintypes
from pathlib import Path

ROOT_DIR = Path(__file__).parent.resolve()
VENV_DIR = ROOT_DIR / "venv"
DOCS_DIR = ROOT_DIR / "Docs"
REQUIREMENTS_FILE = ROOT_DIR / "requirements.txt"

MIN_VERSION = (3, 10, 11)
MIN_VERSION_STR = ".".join(map(str, MIN_VERSION))

VENV_ACTIVATION = VENV_DIR / "Scripts" / "activate.ps1"

class JOBOBJECT_EXTENDED_LIMIT_INFORMATION(ctypes.Structure):
    _fields_ = [
        ("BasicLimitInformation", wintypes.DWORD * 12),
        ("IoInfo", wintypes.DWORD * 6),
        ("ProcessMemoryLimit", ctypes.c_size_t),
        ("JobMemoryLimit", ctypes.c_size_t),
        ("PeakProcessMemoryLimit", ctypes.c_size_t),
        ("PeakJobMemoryLimit", ctypes.c_size_t),
    ]

JOB_OBJECT_LIMIT_KILL_ON_JOB_CLOSE = 0x2000

def run_powershell_live(command_str, capture_output=False):
	job = ctypes.windll.kernel32.CreateJobObjectW(None, None)
	limits = JOBOBJECT_EXTENDED_LIMIT_INFORMATION()
	limits.BasicLimitInformation[4] = JOB_OBJECT_LIMIT_KILL_ON_JOB_CLOSE

	ctypes.windll.kernel32.SetInformationJobObject(
        job, 
        9, # JobObjectExtendedLimitInformation constant
        ctypes.byref(limits), 
        ctypes.sizeof(limits)
    )

	process = subprocess.Popen(
		["powershell", "-ExecutionPolicy", "Bypass", "-Command", command_str], 
		stdout=subprocess.PIPE
	)

	ctypes.windll.kernel32.AssignProcessToJobObject(job, int(process._handle))

	captured_lines = []

	try:
		while True:
			raw_line = process.stdout.readline()
			if not raw_line and process.poll() is not None:
				break
			if raw_line:
				decoded_line = raw_line.decode('utf-8', errors='replace')
				if capture_output:
					captured_lines.append(decoded_line)
				else:
					print(decoded_line, end='')
	except KeyboardInterrupt:
		print("\nProcess interrupted by user. Stopping...")
	finally:
		if process.poll() is None:
			process.terminate()
			process.wait()
		ctypes.windll.kernel32.CloseHandle(job)

	if process.returncode != 0:
		print(f"\nCommand failed with exit code: {process.returncode}")
		sys.exit(process.returncode)

	return captured_lines if capture_output else None