import click
# import requests
#
# from src.config import API

import click

from src.scripts.modules.Ine5613Application import Ine5613Application

@click.group()
def cli():
    """This script showcases different terminal UI helpers in Click."""
    pass


@cli.command()
def start():
    app = Ine5613Application()
    app.run()
    """Start application"""

