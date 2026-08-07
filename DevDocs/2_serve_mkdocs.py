import common

def main():
	print("Launching 'mkdocs serve' in virtual environment for testing (faster than mike serve)\n(Press Ctrl+C to stop)... \n")

	common.run_shell_live([
		f'cd "{common.DOCS_DIR}"',
		"mkdocs serve"
    ])

if __name__ == "__main__":
    main()
