import os

path = os.path.dirname(os.path.realpath(__file__))
cmd = f"""
cd {path}
python3 -m venv .venv
. .venv/bin/activate
pip install --editable .
"""

os.system(cmd)
