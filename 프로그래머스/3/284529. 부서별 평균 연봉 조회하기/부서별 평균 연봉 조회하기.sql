select
    d.DEPT_ID,
    DEPT_NAME_EN,
    round(avg(SAL)) as AVG_SAL
from
    HR_DEPARTMENT d
    join
    HR_EMPLOYEES e on d.DEPT_ID = e.DEPT_ID
group by
    DEPT_NAME_EN, DEPT_ID
order by
    AVG_SAL desc