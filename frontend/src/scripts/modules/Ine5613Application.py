from textual.app import App, ComposeResult
from textual.widgets import Header, Footer, Label

from src.scripts.modules.web.HttpClient import HttpClient


class Ine5613Application(App):
    BINDINGS = [("d", "toggle_dark", "Toggle dark mode")]

    def compose(self) -> ComposeResult:
        http = HttpClient()
        """Create child widgets for the app."""
        yield Header()
        yield Label(str(http.get("/exemplo/teste").json()))
        yield Footer()

    def action_toggle_dark(self) -> None:
        """An action to toggle dark mode."""
        self.dark = not self.dark
