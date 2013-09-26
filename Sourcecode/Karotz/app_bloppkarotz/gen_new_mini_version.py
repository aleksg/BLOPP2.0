import os, zipfile, re

zipreg = r'^.*\.zip$'
pyreg = r'^.*\.py$'
dir = 'app_bloppkarotz'
descriptor_fn = 'descriptor.xml'
zip_fn = 'blopp.zip'

# first, change descriptor version number
with open(descriptor_fn, 'r') as descriptor:
    text = descriptor.read()
    with open(descriptor_fn, 'w') as descriptor:
        starttag = '<version>'
        endtag = '</version>'
        beforetag = text[:text.find(starttag)]
        aftertag = text[text.find(endtag)+len(endtag):]
        ver = text[text.find(starttag)+len(starttag):]
        ver = ver[:ver.find(endtag)]
        
        tinyveri = ver.rfind('.')
        ver = ver[:tinyveri] + '.' + str(int(ver[tinyveri + 1:]) + 1)
        
        newtext = beforetag + starttag + ver + endtag + aftertag
        print 'new vernum: ' + ver
        descriptor.write(newtext)

if zip_fn in os.listdir('.'):
    os.remove(zip_fn)

with zipfile.ZipFile(zip_fn, 'w') as zip:
    def write_zip(dir):
        for fname in os.listdir(dir):
            if not (re.match(zipreg, fname) or re.match(pyreg, fname)):
                p = dir + '/' + fname
                if os.path.isdir(p):
                    write_zip(p)
                else:
                    zip.write(p, p, zipfile.ZIP_DEFLATED)
    write_zip('.')