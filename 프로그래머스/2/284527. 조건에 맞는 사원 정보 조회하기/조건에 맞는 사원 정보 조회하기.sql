select
    sum(g.SCORE) as SCORE,
    g.EMP_NO,
    e.EMP_NAME,
    e.POSITION,
    e.EMAIL
from
    HR_EMPLOYEES e
    join
    HR_GRADE g
        on e.EMP_NO = g.EMP_NO
group by
    EMP_NO
order by
    score desc
limit
    1















# select
#     sum(SCORE) as SCORE,
#     e.EMP_NO,
#     e.EMP_NAME,
#     e.POSITION,
#     e.EMAIL
# from
#     HR_EMPLOYEES e
#     join
#     HR_GRADE g
#         on e.EMP_NO = g.EMP_NO
# group by
#     g.EMP_NO
# order by
#     SCORE desc
# limit
#     1