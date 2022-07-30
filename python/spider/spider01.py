from bs4 import BeautifulSoup

if __name__ == '__main__':
    print("爬虫开始")
    words = [
        'take place',
        'word',
    ]
    for word in words:
        print(word)


def first_step(word):
    url = 'http://bncweb.lancs.ac.uk/cgi-binbncXML/processQuery.pl'
    qs_params = {
        'theData': 'take+place',
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
    soup = BeautifulSoup.encode('<div>123</div>')
    pass


def do_spider():
    pass


def second_step():
    url = 'http://bncweb.lancs.ac.uk/cgi-binbncXML/dlogs.pl?selected=Distribution&inst=50&max=66&chunk=1&qname=Rhea123_1657765571&queryID=Rhea123_1657765571&thMode=M3259%231366%23no_subcorpus%23%23&thin=0&theData=%5Bword%3D%22take%22%25c%5D+%5Bword%3D%22place%22%25c%5D&warned=0&numOfSolutions=3259&numOfFiles=&numOfSpeakers=138&view=list&dbname=&SQL=&letter=&phon=0&tag=&ot=&display=word&exclude=&program=search&run=&theID=Rhea123_1657765571&urlTest=yes'
    qs_params = {
        '': 1
    }
    pass
