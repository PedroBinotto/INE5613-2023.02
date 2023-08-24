import os

cmd = f"""
python3 -m venv .venv
. .venv/bin/activate
pip install --editable .
"""

os.system(cmd)
