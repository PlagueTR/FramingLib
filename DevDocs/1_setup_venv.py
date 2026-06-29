import sys
import venv
import common

def main():
    print("Checking system requirements...")
	
    current_version = sys.version_info[:3]
    version_str = ".".join(map(str, current_version))

    if current_version < common.MIN_VERSION:
        print(f"Error: Your Python version ({version_str}) is too old.")
        print(f"Please run this script using Python {common.MIN_VERSION_STR} or higher.")
        sys.exit(1)

    print(f"Success: Valid Python version found: {version_str}")

    if not common.VENV_DIR.is_dir():
        print(f"Creating virtual environment '{common.VENV_DIR}'...")
        venv.create(common.VENV_DIR, with_pip=True)
    else:
        print(f"Virtual environment '{common.VENV_DIR}' already exists. Skipping creation.")

    print("Upgrading pip inside the virtual environment...\n")
    common.run_powershell_live(
        f". '{common.VENV_ACTIVATION}'; "
        "python -m pip install --upgrade pip"
    )

    print(f"\nInstalling packages from {common.REQUIREMENTS_FILE}...\n")
    common.run_powershell_live(
        f". '{common.VENV_ACTIVATION}'; "
        f"python -m pip install -r '{common.REQUIREMENTS_FILE}'"
    )

    print("\nEnvironment setup completed successfully.")

if __name__ == "__main__":
    main()
