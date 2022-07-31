import re
import requests
from bs4 import BeautifulSoup
from urllib import parse


def first_step(word):
    url = 'http://bncweb.lancs.ac.uk/cgi-binbncXML/processQuery.pl?chunk=1&queryType=CQL&qMode=Simple+query+%28ignore+case%29&inst=50&max=INIT&qname=INIT&thMode=INIT&thin=0&qtype=0&view=list&phon=0&theAction=Start+Query&urlTest=yes'
    # _word = "+".join(word.split(' '))
    qs_params = {
        'theData': word,
    }
    # 这里不应该是 encode！因该是解析 querystring
    qs = parse.urlencode(qs_params)
    url = url + '&' + qs
    # print(url)
    headers = {
        'Authorization': 'Basic UmhlYTEyMzpEaW5ncnVpNTIw',
    }
    response = requests.get(url, headers=headers)
    # print(response.text)
    soup = BeautifulSoup(response.text, features="html.parser")
    return {
        'inst': soup.select('input[name=inst]')[0]['value'],
        'max': soup.select('input[name=max]')[0]['value'],
        'chunk': soup.select('input[name=chunk]')[0]['value'],
        'thMode': soup.select('input[name=thMode]')[0]['value'],
        'theData': soup.select('input[name=theData]')[0]['value'],
        'numOfSolutions': soup.select('input[name=numOfSolutions]')[0]['value'],
        'numOfSpeakers': soup.select('input[name=numOfSpeakers]')[0]['value'],
        'qname': soup.select('input[name=qname]')[0]['value'],
        'queryID': soup.select('input[name=qname]')[0]['value'],
        'theID': soup.select('input[name=qname]')[0]['value'],
    }


def second_step(qs_param):
    url = 'http://bncweb.lancs.ac.uk/cgi-binbncXML/dlogs.pl?selected=Distribution&thin=0&warned=0&numOfFiles=&view=list&dbname=&SQL=&letter=&phon=0&tag=&ot=&display=word&exclude=&program=search&run=&urlTest=yes'
    qs = parse.urlencode(qs_param)
    url = url + '&' + qs
    # print(url)
    headers = {
        'Authorization': 'Basic UmhlYTEyMzpEaW5ncnVpNTIw',
    }
    response = requests.get(url, headers=headers)
    soup = BeautifulSoup(response.text, features="html.parser")
    sc = soup.find('td', text=re.compile('Spoken conversation')).parent.select('td')[4].text
    osm = soup.find('td', text=re.compile('Other spoken material')).parent.select('td')[4].text
    fav = soup.find('td', text=re.compile('Fiction and verse')).parent.select('td')[4].text
    opwm = soup.find('td', text=re.compile('Other published written material')).parent.select('td')[4].text
    n = soup.find('td', text=re.compile('Newspapers')).parent.select('td')[4].text
    uwm = soup.find('td', text=re.compile('Unpublished written material')).parent.select('td')[4].text
    napab = soup.find('td', text=re.compile('Non-academic prose and biography')).parent.select('td')[4].text
    ap = soup.find('td', text=re.compile('Academic prose')).parent.select('td')[4].text
    print(sc, ',', osm, ',', fav, ',', opwm, ',', n, ',', uwm, ',', napab, ',', ap)


def do_spider(word):
    qs_param = first_step(word)
    second_step(qs_param)


# 这是程序开始的地方
if __name__ == '__main__':
    words = [
        'put something',
        '9812nnkzxcjk12n50',
        'take place',
        'word',
    ]
    for word in words:
        try:
            do_spider(word)
        except Exception:
            print('')
