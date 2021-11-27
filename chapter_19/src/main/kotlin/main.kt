fun main() {
    val user = User()
    user.firstName = "Bob"
    user.lastName = "Barker"

    println("User info:\n$user")

    val billingAddress = Address("123 Fake Street",
        "4th floor",
        "Los Angeles",
        "CA",
        "90291",
        AddressType.Billing)

    println("Billing Address:\n$billingAddress\n")

    user.addOrUpdateAddress(billingAddress)
    println("User info after adding address:\n$user")

    val shippingAddress = Address("987 Unreal Drive",
        null,
        "Burbank",
        "CA",
        "91523",
        AddressType.Shipping)
    user.addOrUpdateAddress(shippingAddress)
    println("User info after adding address:\n$user")

    println("Shipping Label:")
    printLabelFor(user)

    val anotherUser = User()

    println("Another User has ${anotherUser.addresses?.count()} addresses")
    println("Another User first name: ${anotherUser.firstName ?: "(not set)"}")

    println("Sample First Line: ${Address.sampleFirstLine}")
    println("Sample Canadian Address:\n${Address.canadianSample(AddressType.Billing)}")
}