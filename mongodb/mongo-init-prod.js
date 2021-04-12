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