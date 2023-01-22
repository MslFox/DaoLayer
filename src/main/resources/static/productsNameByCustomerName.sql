select o.productName from Customer c
join Order o on o.customer = c
where c.name=:name
