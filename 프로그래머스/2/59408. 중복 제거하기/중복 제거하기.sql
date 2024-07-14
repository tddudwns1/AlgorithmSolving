SELECT count(i.name) as count
from (
    select distinct name
    from animal_ins
) i
where i.name is not null