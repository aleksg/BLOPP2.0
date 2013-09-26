import os

start_dir = 'blopp'
out_fn = 'filelist.txt'

def write_folder(out, dir):
    for fname in os.listdir(dir):
        if os.path.isdir(dir + '/' + fname):
            write_folder(out, dir + '/' + fname)
        else:
            out.write(dir + '/' + fname + '\n')

with open(out_fn, 'w') as out:
    write_folder(out, start_dir)