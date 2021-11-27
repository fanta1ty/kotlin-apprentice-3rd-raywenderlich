@file:JvmName("UserExtensions")
val User.fullName: String
    get() = "$firstName $lastName"

fun User.addressOfType(type: AddressType): Address? {
    return addresses?.firstOrNull { it.addressType == type }
}

fun User.addOrUpdateAddress(address: Address) {
    val existingOfType = addressOfType(address.addressType)

    if (existingOfType != null) {
        addresses?.remove(existingOfType)
    }

    addresses?.add(address)
}