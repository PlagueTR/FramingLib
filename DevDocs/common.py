import sys
import subprocess
import os
from pathlib import Path

ROOT_DIR = Path(__file__).parent.resolve()
VENV_DIR = ROOT_DIR / "venv"
DOCS_DIR = ROOT_DIR / "Docs"
REQUIREMENTS_FILE = ROOT_DIR / "requirements.txt"

MIN_VERSION = (3, 10, 11)
MIN_VERSION_STR = ".".join(map(str, MIN_VERSION))

if os.name == "nt":
    SHELL_EXEC = ["cmd.exe", "/c"]
    VENV_ACTIVATION = VENV_DIR / "Scripts" / "activate.bat"
    CMD_CONNECTOR = " && "
    ACTIVATE_COMMAND = f'call "{VENV_ACTIVATION}"'
else:
    SHELL_EXEC = ["/bin/sh", "-c"]
    VENV_ACTIVATION = VENV_DIR / "bin" / "activate"
    CMD_CONNECTOR = " && "
    ACTIVATE_COMMAND = f'. "{VENV_ACTIVATION}"'

def run_shell_live(command_payload:list[str], capture_output=False):

    payload_str = CMD_CONNECTOR.join(command_payload)
    full_command_str = f"{ACTIVATE_COMMAND}{CMD_CONNECTOR}{payload_str}"

    spawn_args = SHELL_EXEC + [full_command_str]

    captured_lines = []

    process = None
    try:
        process = subprocess.Popen(
            spawn_args,
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,
            text=True,
            encoding='utf-8',
            errors='replace'
        )

        assert process.stdout is not None

        while True:
            line = process.stdout.readline()
            if not line and process.poll() is not None:
                break
            if line:
                if capture_output:
                    captured_lines.append(line)
                else:
                    print(line, end='')

    except KeyboardInterrupt:
        print("\nProcess interrupted by user. Stopping...")

    finally:
        if process is not None and process.poll() is None:
            process.terminate()
            process.wait()

    return_code = process.returncode if process else -1
    if return_code != 0:
        print(f"\nCommand failed with exit code: {return_code}")
        sys.exit(return_code)

    return captured_lines if capture_output else None
