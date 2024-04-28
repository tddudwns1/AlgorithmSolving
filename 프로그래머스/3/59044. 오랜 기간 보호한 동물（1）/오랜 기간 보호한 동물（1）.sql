-- 코드를 입력하세요
SELECT i.name as name, i.datetime as datetime
from animal_ins i
left join animal_outs o using (animal_id)
where o.animal_id is null
order by i.datetime
limit 3;