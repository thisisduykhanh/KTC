## yêu cầu 1

```sql
db.products.insertMany([
    {
        name: "san pham 1",
        price: 30,
        category: "Quần Jean"
    },
    {
        name: "san pham 2",
        price: 45,
        category: "Áo Khoác"
    },
    {
        name:"san pham 3",
        price: 20,
        category: "Nón Len"
    }
])

```

## yêu cầu 2

```sql
db.orders.insertMany([
   {
    orderId: "ORD001",
    customerName: "Nguyen Van A",
    orderDate: ISODate("2025-08-01T00:00:00Z"),
    totalAmount: 2000
   },
   {
    orderId: "ORD002",
    customerName: "Tran Thi B",
    orderDate: ISODate("2025-08-02T00:00:00Z"),
    totalAmount: 350
   }
   ])

```

## yêu cầu 3

```sql

db.users.insertMany([
    {
        name: "Alice",
        email: "alice@example.com",
        age: 30
    },
    {
        name: "Bob",
        email: "bob@example.com",
        age: 19
    },
    {
        name: "Charlie",
        email: "charlie@example.com",
        age: 27
    },
    {
        name: "David",
        email: "david@example.com",
        age: 35
    },
    {
        name: "Eve",
        email: "eve@example.com",
        age: 22
    }
])
```

## yêu cầu 4

```sql
db.users.find(
    { age: { $gt: 25 } },
    { _id: 0, name: 1, email: 1 }
)

```

## yêu cầu 5

```sql
db.users.updateOne(
    { name: "Alice" },
    { $set: { age: 31 } }
    )
```

## yêu cầu 6

```sql
db.users.deleteMany({ age: { $lt: 20 } })
```

## yêu cầu 7

```sql
db.users.find().sort({ age: -1 }).limit(3)

```

## yêu cầu 8

```sql
db.users.find(
    {},
    { _id: 0, name: 1, age: 1 }
    ).sort({ age: -1 }).limit(3)

```

## yêu cầu 9

```sql
db.users.aggregate([
    {
        $group: {
        _id: "$age",
        count: { $sum: 1 }
        }
    }
])

```

## yêu cầu 10

```sql
db.users.aggregate([
    {
        $match: { age: { $gte: 25 } }
    },
    {
        $group: {
        _id: null,
        averageAge: { $avg: "$age" }
        }
    }
])

```
