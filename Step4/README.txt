This directory contains artifacts of analysis done by Designate code quality assessment tool (.csv files), programs to determine co-occurrences between design smells and architectural smells (.py files) and output from the programs (.txt files).

summary_as.py is used to determine co-occurrences between architectural smells
summary_ds.py is used to determine co-occurrences between design smells
summary_as_mod.py is used to determine architectural smells in modules
summary_ds_mod.py is used to determine design smells in modules

All of these programs produce 2 files - 
modules file that contains column of packages where smell was found
Values file that contains column of occurrences per package

