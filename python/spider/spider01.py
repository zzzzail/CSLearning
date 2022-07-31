from bs4 import BeautifulSoup
from urllib import parse
import requests


def first_step(word):
    url = 'http://bncweb.lancs.ac.uk/cgi-binbncXML/processQuery.pl'
    _word = "+".join(word.split(' '))
    qs_params = {
        'theData': _word,
        'chunk': 1,
        'queryType': 'CQL',
        'qMode': 'Simple+query+%28ignore+case%29',
        'inst': 50,
        'max': 'INIT',
        'qname': 'INIT',
        'thMode': 'INIT',
        'thin': 0,
        'qtype': 0,
        'view': 'list',
        'phon': 0,
        'theAction': 'Start+Query',
        'urlTest': 'yes',
    }
    # 这里不应该是 encode！因该是解析 querystring
    qs = parse.urlencode(qs_params)
    url = url + '?' + qs
    print(url)
    headers = {
        'Authorization': 'Basic UmhlYTEyMzpEaW5ncnVpNTIw',
    }
    response = requests.get(url, headers=headers)
    print(response.text)


def second_step():
    url = 'http://bncweb.lancs.ac.uk/cgi-binbncXML/dlogs.pl'
    qs_params = {
        'selected': 'Distribution',
        'inst': 50,
        'max': 66,
        'chunk': 1,
        'qname': 'Rhea123_1657765571',
        'queryID': 'Rhea123_1657765571',
        'thMode': 'M3259%231366%23no_subcorpus%23%23',
        'thin': 0,
        'theData': '%5Bword%3D%22take%22%25c%5D+%5Bword%3D%22place%22%25c%5D',
        'warned': 0,
        'numOfSolutions': 3259,
        'numOfFiles': '',
        'numOfSpeakers': 138,
        'view': list,
        'dbname': '',
        'SQL': '',
        'letter': '',
        'phon': 0,
        'tag': '',
        'ot': '',
        'display': 'word',
        'exclude': '',
        'program': 'search',
        'run': '',
        'theID': 'Rhea123_1657765571',
        'urlTest': 'yes',
    }
    headers = {
        'Authorization': 'Basic UmhlYTEyMzpEaW5ncnVpNTIw',
    }
    pass


def do_spider():
    pass


if __name__ == '__main__':
    first_step('take place')
    pass
    print("爬虫开始")
    words = [
        'take place',
        'word',
    ]
    for word in words:
        print(word)
