select
    left(p.product_code, 2) as category,
    count(*) as products
from
    product p
group by
    category