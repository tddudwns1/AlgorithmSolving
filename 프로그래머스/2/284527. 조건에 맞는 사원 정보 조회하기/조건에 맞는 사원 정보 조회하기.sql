select
    sum(SCORE) as SCORE,
    e.EMP_NO,
    e.EMP_NAME,
    e.POSITION,
    e.EMAIL
from
    HR_EMPLOYEES e
    join
    HR_GRADE g
        on e.EMP_NO = g.EMP_NO
group by
    g.EMP_NO
order by
    SCORE desc
limit
    1

# 2022년도 한해 평가 점수(상,하반기 점수의 합) 가 가장 높은 사원



