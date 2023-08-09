A = [[0 for _ in range(100)] for _ in range(100)]
cnt = int(input())
for _ in range(cnt):
    w, h = map(int, input().split())
    for i in range(w, w + 10):
        for j in range(h, h + 10):
            A[i][j] = 1
tot = 0
print(sum([sum(row) for row in A]))