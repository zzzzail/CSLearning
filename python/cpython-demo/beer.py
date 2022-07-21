#!/usr/bin/env python3

"""
A Python version of the classic "bottles of beer on the wall" programming
example.

墙上的啤酒瓶
100个酒瓶在墙上，打下一个来，墙上有99个酒瓶。
99个酒瓶在墙上，打下一个来，墙上有98个酒瓶。

By Guido van Rossum, demystified after a version by Fredrik Lundh.
"""

import sys

n = 100
if sys.argv[1:]:
    n = int(sys.argv[1])


def bottle(n):
    if n == 0: return "no more bottles of beer"
    if n == 1: return "only one bottle of beer"
    return str(n) + " bottles of beer"


# range(stop)
# range(start, stop[, step])
for i in range(n, 0, -1):
    print(bottle(i), "on the wall,")
    print(bottle(i) + ".")
    print("Take one down, pass it around,")
    print(bottle(i - 1), "on the wall.")


def bot(n: int):
    if n == 0:
        return "zero"
    else:
        return n

# for 循环左闭右开 [100, 0)
for i in range(100, -1, -1):
    if i == 0:
        print(bot(i), "个酒瓶在墙上。全部都打完了，收拾东西回家吃饭。")
        break
    print(bot(i), "个酒瓶在墙上，打下一个来，墙上还有", bot(i - 1), "个酒瓶。")
