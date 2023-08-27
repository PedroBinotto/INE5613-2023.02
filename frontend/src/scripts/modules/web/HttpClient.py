from typing import Dict
from src.config import API
from src.scripts.modules.meta.Singleton import Singleton
from requests import Response, get, post, put, delete


class HttpClient(metaclass=Singleton):
    def __init__(self):
        self.__api = API

    def get(self, url: str) -> Response:
        return get(url=f"{self.__api}{url}")

    def post(self, url: str, data: Dict) -> Response:
        return post(url=f"{self.__api}{url}", data=data)

    def put(self, url: str, data: Dict) -> Response:
        return put(url=f"{self.__api}{url}", data=data)

    def delete(self, url: str) -> Response:
        return delete(f"{self.__api}{url}")
