use myretaildb
db.createUser(
  {
    user: "myretail",
    pwd: "password123",
    roles: [ 
        {
            role: "readAnyDatabase", db: "admin" 
        },
        "readWrite"
    ] 
  }
)

var data = {}
data = [
  {
    product_id: 13860428,
    price: 13.49,
    currency_code: "USD"
  },
  {
    product_id: 54456119,
    price: 22.66,
    currency_code: "USD"
  },
  {
    product_id: 13264003,
    price: 30.99,
    currency_code: "USD"
  },
  {
    product_id: 12954218,
    price: 50,
    currency_code: "USD"
  }
]
db.priceEntity.save(data)
