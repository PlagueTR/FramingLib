import common

def deploy_version():
	version = input("\nEnter the version to deploy: ").strip()
	if not version:
		print("Error: Version cannot be empty.")
		return

	cmd = ["mike", "deploy", version]
	cmd_str = ' '.join(cmd)

	print(f'\nRunning command: "{cmd_str}"')

	common.run_shell_live([
		f'cd "{common.DOCS_DIR}"',
		f'{cmd_str}'
	])
	print("\nSuccess: Version successfully deployed.")

def set_default_version():
	print("\nRetrieving available deployed versions from mike...\n")
	output_lines = common.run_shell_live([
        f'cd "{common.DOCS_DIR}"',
		"mike list"
        ],capture_output=True
	)

	lines = [line.strip() for line in output_lines if line.strip()]

	if not lines:
		print("\nError: No versions found or failed to retrieve versions from mike.")
		return

	available_targets = []
	print("\nAvailable Deployed Versions/Aliases:")
	for line in lines:
		parts = line.split()
		if not parts:
			continue
		available_targets.append(parts[0])
		for part in parts[1:]:
			clean_part = part.strip("()")
			if clean_part and clean_part not in available_targets:
				available_targets.append(clean_part)

	distinct_targets = list(dict.fromkeys(available_targets))

	for idx, target in enumerate(distinct_targets, start=1):
		print(f' ["{idx}"] "{target}"')

	choice = input("\nSelect a number to set as the default landing version: ").strip()
	choice_idx = int(choice) - 1
	if choice_idx < 0 or choice_idx >= len(distinct_targets):
		print("Invalid selection. Operation canceled.")
		return
	selected_version = distinct_targets[choice_idx]

	cmd = ["mike", "set-default", selected_version]
	cmd_str = ' '.join(cmd)

	print(f'\nRunning command: "{cmd_str}"')

	common.run_shell_live([
        f'cd "{common.DOCS_DIR}"',
		f'{cmd_str}'
    ])
	print("Success: Default version successfully changed.")

def main():
	while True:
		print("\n[1] Deploy a new version")
		print("[2] Set the default landing version")
		print("[3] Exit")

		choice = input("\nChoose an option (1-3): ").strip()

		if choice == "1":
			deploy_version()
		elif choice == "2":
			set_default_version()
		elif choice == "3":
			break
		else:
			print("\nInvalid option. Please enter 1, 2, or 3.")

	print("Goodbye!")

if __name__ == "__main__":
    main()
