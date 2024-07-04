import os
import shutil

# List of directories to process
directories = ['android\\app\\src\\main\\java\\com\\anonymous\\myECPAYapp']
output_file = "output.txt"

# Define the destination directory for the copy
destination_root = "copied_directories"

# Create the destination root directory if it doesn't exist
os.makedirs(destination_root, exist_ok=True)

with open(output_file, "w") as file:
    for dir_name in directories:
        if os.path.isdir(dir_name):
            # Create a unique destination directory name
            dest_dir_name = os.path.join(destination_root, os.path.basename(dir_name))
            
            # Copy the directory
            shutil.copytree(dir_name, dest_dir_name)
            
            file.write(f"Copied Directory: {dest_dir_name}\n")
            for root, dirs, files in os.walk(dest_dir_name):
                # Skip __pycache__ directories
                dirs[:] = [d for d in dirs if d != "__pycache__"]

                # Write directory names
                for dir in dirs:
                    file.write(f"Subdirectory: {os.path.join(root, dir)}\n")

                # Write file names and contents
                for file_name in files:
                    file_path = os.path.join(root, file_name)
                    file.write(f"File: {file_path}\n")
                    try:
                        with open(file_path, "r") as f:
                            content = f.read()
                        file.write(f"Contents:\n{content}\n")
                    except Exception as e:
                        file.write(f"Error reading file: {e}\n")
            file.write("\n")
        else:
            print(f"Directory not found: {dir_name}")

print(f"Content written to {output_file}")
