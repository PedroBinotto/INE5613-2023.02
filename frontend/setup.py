from setuptools import setup, find_packages

setup(
    name='ine5613',
    version='0.1.0',
    packages=find_packages(),
    include_package_data=True,
    install_requires=[
        'Click',
        'textual',
        'textual-dev',
        'requests'
    ],
    entry_points={
        'console_scripts': [
            'ine5613 = src.scripts.main:cli',
        ],
    },
)