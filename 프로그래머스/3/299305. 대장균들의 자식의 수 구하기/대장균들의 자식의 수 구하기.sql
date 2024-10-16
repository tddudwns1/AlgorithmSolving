-- 코드를 작성해주세요
select
    p.ID as ID,
    count(c.PARENT_ID) as CHILD_COUNT
from
    ECOLI_DATA p
    left join
    ECOLI_DATA c
        on c.PARENT_ID = p.ID
group by
    ID
order by
    ID