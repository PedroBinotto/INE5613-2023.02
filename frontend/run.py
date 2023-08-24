import os

NAME = "ine5613"

path = os.path.dirname(os.path.realpath(__file__))
cmd = f"""
cd {path}/src
. .venv/bin/activate
{NAME}
"""

os.system(cmd)
