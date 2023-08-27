import click

# import requests
#
# from src.config import API

import click

from src.scripts.modules.Ine5613Application import Ine5613Application


@click.group()
def cli():
    """
    Frontend para o projeto final da disciplina de Bancos de Dados I

    Configuração padrão do proxy realiza requests para <http://localhost:8080>
    """
    pass


@cli.command()
def start():  # TODO: Incluir opção para parametrizar endpoint da API
    """Inicializa a aplicação"""
    app = Ine5613Application()
    app.run()
