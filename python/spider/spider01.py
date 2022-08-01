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
    # print(sc, ',', osm, ',', fav, ',', uwm, ',', opwm, ',', n, ',', ap, ',', napab)
    return [sc, osm, fav, uwm, opwm, n, ap, napab]


def do_spider(word):
    qs_param = first_step(word)
    res = second_step(qs_param)
    return res


# 这是程序开始的地方
if __name__ == '__main__':
    words = [
        "{express/V} * opinions",
        "{develop/V} confidence",
        "{take/V} turns",
        "{play/V} sports",
        "{surf/V} the Internet",
        "{go/V} running",
        "{drag/V} _PNX away from",
        "from time to time",
        "{set/V} * goals",
        "{work/V} hard",
        "every minute counts",
        "{reduce/V} stress",
        "{reduce/V} tension",
        "{suffer/V} from stress",
        "{do/V} * exams",
        "{sit/V} around",
        "{listen/V} to * music",
        "{pick/V} out",
        "{live/V} up to",
        "{flash/V} through _PNX mind",
        "{be/V} responsible for",
        "{feel/V} satisfied",
        "{be/V} able to",
        "{be/V} confident in",
        "{bring/V} changes to",
        "{make/V} * contribution to",
        "{become/V} popular",
        "{treat/V} as",
        "{feel/V} ready to",
        "{look/V} forward to",
        "{pay/V} attention to",
        "{improve/V} the ability",
        "{come/V} up with",
        "{solve/V} _AT0 problem",
        "{play/V} _AT0 * part",
        "{crop/V} up",
        "{cut/V} off",
        "{come/V} to a conclusion",
        "{relieve/V} tension",
        "{watch/V} * films",
        "{do/V} voluntary work",
        "{take/V} care of",
        "{get/V} up",
        "{play/V} * music",
        "{keep/V} fit",
        "{play/V} basketball",
        "{do/V} * {exercise/N}",
        "{do/V} * {gymnastics/N}",
        "{go/V} walking",
        "{go/V} to the gym",
        "{be/V} in (_AJ0 | _AJC | _AJS) shape",
        "{have/V} a healthy diet",
        "{have/V} a balanced diet",
        "{take/V} the lift",
        "{walk/V} up the stairs",
        "{make/V} a guess",
        "{crash/V} into",
        "{earn/V} (_DPS | _POS) place",
        "{get/V} hurt",
        "{leave/V} out",
        "{feed/V} up with",
        "{get/V} sick",
        "{feel/V} awkward",
        "{make/V} sure",
        "{give/V} up",
        "{take/V} breaks",
        "{kill/V} time",
        "{speed/V} up",
        "{take/V} advantage of",
        "{reflect/V} on",
        "{move/V} away",
        "used to",
        "{catch/V} the attention",
        "{get/V} started",
        "{look/V} for",
        "{improve/V} flexibility",
        "{improve/V} strength",
        "{work/V} out",
        "{burn/V} fat",
        "{recover/V} from",
        "{do/V} (_DPS | _POS) best",
        "{improve/V} mood",
        "{do/V} stretches",
        "{have/V} dinner",
        "{watch/V} fireworks",
        "{have/V} a family gathering",
        "{get/V} together",
        "{put/V} up decorations",
        "{let/V} off fireworks",
        "from top to bottom",
        "{sweep/V} away",
        "{put/V} upside down",
        "{scare/V} away",
        "{get/V} married",
        "{get/V} on the train",
        "{prepare/V} for",
        "{gather/V} around",
        "as early as possible",
        "tens of thousands of",
        "{create/V} (a | an) * atmosphere",
        "{give/V} thanks",
        "{come/V} to the end",
        "{hang/V} a stocking",
        "{think/V} of",
        "{find/V} out",
        "{move/V} in",
        "{make/V} (a | an) * effort to",
        "{take/V} a seat",
        "{fill/V} with",
        "{fall/V} asleep",
        "{call/V} over",
        "{catch/V} one's eye",
        "{fall/V} off",
        "{pass/V} away",
        "{come/V} to mind",
        "{be/V} in a poor health condition",
        "{wake/V} up",
        "{get/V} dressed",
        "{take/V} a * time",
        "{calm/V} down",
        "{focus/V} on",
        "{base/V} on",
        "{solve/V} riddles",
        "{be/V} worth _VVG",
        "{express/V} hopes",
        "{express/V} wishes",
        "{gather/V} together",
        "{stand/V} for",
        "{gather/V} fortune",
        "{beg/V} (_DPS | _POS) pardon",
        "{work/V} the sum",
        "{give/V} (_AJ0 | _AJC | _AJS) advice",
        "{hang/V} decorations",
        "{get/V} ready for",
        "{put/V} up",
        "{give/V} out",
        "{dress/V} appropriately",
        "{put/V} in prison",
        "{agree/V} with",
        "{be/V} in peace",
        "{go/V} to bed",
        "{have/V} * impact on",
    ]
    for word in words:
        try:
            res = do_spider(word)
            print(word, ', ', ', '.join(res))
        except Exception:
            print(word, ', ')
