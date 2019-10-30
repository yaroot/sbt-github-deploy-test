import os
import requests
import logging

logging.basicConfig()
# logging.getLogger().setLevel(logging.DEBUG)
# requests_log = logging.getLogger("requests.packages.urllib3")
# requests_log.setLevel(logging.DEBUG)
# requests_log.propagate = True

GITHUB_USER = os.environ['GITHUB_USER']
GITHUB_REPO = os.environ['GITHUB_REPO']
GITHUB_TOKEN = os.environ['GITHUB_TOKEN']

REPO_BASE = 'https://maven.pkg.github.com'
LOCAL_REPO_BASE = os.path.expanduser('~/.m2/repository')


def upload(file):
    """
         file = 'com/github/foo/bar/1.0.0/bar.pom'
    """
    with open(os.path.join(LOCAL_REPO_BASE, file), 'rb') as f:
        r = requests.put(
            '/'.join((REPO_BASE, GITHUB_USER, GITHUB_REPO, file)),
            auth=(GITHUB_USER, GITHUB_TOKEN),
            data=f,
        )
        print('Uploaded', file,  r, r.content)
    pass


def scan_for_pom(base):
    for root, dirs, files in os.walk(LOCAL_REPO_BASE):
        artifacts = [
            f
            for f in files
            if f.endswith('.pom') or f.endswith('.jar')
        ]

        if artifacts and [f for f in artifacts if f.endswith('.pom')]:
            pkgbase = root[len(base)+1:]
            yield (pkgbase, artifacts)
    pass


def main():
    for pkgbase, artifacts in scan_for_pom(LOCAL_REPO_BASE):
        print('>> Uploading', pkgbase)
        arts = sorted(artifacts, key=lambda x: 1 if x.endswith('.pom') else 2)
        for f in arts:
            upload(os.path.join(pkgbase, f))
    pass


if __name__ == '__main__':
    main()
