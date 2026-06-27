import os
import re
import shutil

def setup_project():
	print("Template Generator")

	mod_id = input("Enter Mod ID: ").strip()
	mod_group = input("Enter Mod Maven Group: ").strip()

	old_mod_id = "example_mod"
	old_mod_group = "com.example"

	print("Moving directories")

	old_package_path = os.path.join(*old_mod_group.split('.'), old_mod_id)
	new_package_path = os.path.join(*mod_group.split('.'), mod_id)

	projects = ['common', 'fabric', 'forge', 'neoforge']

	for project in projects:
		src_root = os.path.join(project, 'src', 'main', 'java')
		if not os.path.exists(src_root):
			continue

		old_dir = os.path.join(src_root, old_package_path)
		new_dir = os.path.join(src_root, new_package_path)

		if os.path.exists(old_dir):
			os.makedirs(os.path.dirname(new_dir), exist_ok=True)
			shutil.copytree(old_dir, new_dir, dirs_exist_ok=True)
			shutil.rmtree(os.path.join(src_root, old_mod_group.split('.')[0]))

	print("Updating occurrences")

	replacements = {
		f"{old_mod_group}.{old_mod_id}": f"{mod_group}.{mod_id}",
		old_mod_group: mod_group,
		old_mod_id: mod_id
	}

	valid_extensions = ('.json', '.toml', '.java', '.cfg', '.properties', '.conf')

	for root, dirs, files in os.walk('.'):
		if any(p in root for p in ['.git', '.gradle', '.idea', 'build', '.github']):
			continue

		for file in files:
			if file.endswith(valid_extensions):
				file_path = os.path.join(root, file)

				if file == 'project_setup.py':
					continue
				
				try:
					with open(file_path, 'r', encoding='utf-8') as f:
						content = f.read()
					
					modified = False

					if file == 'gradle.properties':
						content = re.sub(r'maven_group\s*=\s*.*', f'maven_group = {mod_group}', content)
						content = re.sub(r'archives_name\s*=\s*.*', f'archives_name = {mod_id}', content)
						modified = True
					else:
						for old, new in replacements.items():
							if old in content:
								content = content.replace(old, new)
								modified = True
					
					if modified:
						with open(file_path, 'w', encoding='utf-8') as f:
							f.write(content)
				
				except Exception as e:
					print(f"Failed updating file: {file_path}")
	
	print("Renaming directories")
	dirs_to_rename = []
	for root, dirs, files in os.walk('.'):
		if any(p in root for p in ['.git', '.gradle', 'build', '.idea']):
			continue
		for d in dirs:
			if old_mod_id in d:
				full_path = os.path.join(root, d)
				dirs_to_rename.append(full_path)
                
	dirs_to_rename.sort(key=len, reverse=True)

	for old_dir_path in dirs_to_rename:
		parent_dir = os.path.dirname(old_dir_path)
		old_name = os.path.basename(old_dir_path)
		new_name = old_name.replace(old_mod_id, mod_id)
		new_dir_path = os.path.join(parent_dir, new_name)
		os.rename(old_dir_path, new_dir_path)

	print("Renaming files")
				
	for root, dirs, files in os.walk('.'):
		if any(p in root for p in ['.git', '.gradle', 'build']):
			continue

		for file in files:
			if old_mod_id in file:
				old_file_path = os.path.join(root, file)
				new_filename = file.replace(old_mod_id, mod_id)
				new_file_path = os.path.join(root, new_filename)
				os.rename(old_file_path, new_file_path)

	print("Done! Please delete the setup script manually")

if __name__ == "__main__":
	setup_project()