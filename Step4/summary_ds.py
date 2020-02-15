import csv

def findnth(haystack, needle, n):
    parts= haystack.split(needle, n+1)
    if len(parts)<=n+1:
        return -1
    return len(haystack)-len(parts[-1])-len(needle)

def get_module_name(text):
    start = findnth(text, '.', 0) + 1
    if (findnth(text, '.', 3)) != -1:
        end = findnth(text, '.', 3)
        return text[start:end]
    else:
        return text[start:]

input_file_name = 'DesignSmells.csv'
smell_name = 'Unutilized abstraction'
modules_dict = {}

with open(input_file_name, 'rt') as f:
     reader = csv.reader(f, delimiter=',') # good point by @paco
     for row in reader:
         if smell_name.lower() in row[3].lower():
             module_name = get_module_name(row[1])
             if (module_name in modules_dict):
                 modules_dict[module_name] += 1
             else: 
                 modules_dict[module_name] = 1

f_modules = open('design_smells_modules.txt','w') 
f_values = open('design_smells_values.txt','w') 

for module in modules_dict:
    f_modules.write(f'{module}\n')
    f_values.write(f'{modules_dict[module]}\n')
