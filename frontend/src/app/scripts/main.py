import click
import requests

from app.config import API

@click.command()
def cli():
    """Example script."""
    response = requests.get(f"{API}/exemplo/teste")
    click.echo(response.json())
