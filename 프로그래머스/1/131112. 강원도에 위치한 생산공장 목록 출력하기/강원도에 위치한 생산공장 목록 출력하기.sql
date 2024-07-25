SELECT
    f.factory_id as factory_id,
    f.factory_name as factory_name,
    f.address as address
from
    food_factory f
where
    f.address like "강원도%"
order by f.factory_id