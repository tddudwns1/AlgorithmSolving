-- 코드를 입력하세요
SELECT i.animal_id as animal_id, i.name as name
from animal_ins i
join animal_outs o using (animal_id)
order by DATEDIFF(o.DATETIME, i.DATETIME) DESC
limit 2;