SELECT
    f.flavor as flavor
from
    first_half f
group by
    f.flavor
order by
    sum(f.total_order) desc,
    f.shipment_id