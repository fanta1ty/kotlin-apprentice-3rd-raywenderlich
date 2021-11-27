@file:JvmName("LabelPrinter")
fun labelFor(user: User, type: AddressType): String {
    val address = user.addressOfType(type)
    if (address != null) {
        var label = "-----\n"
        label += "${user.fullName}\n${address.forPostalLabel()}\n"
        label += "-----\n"
        return label
    } else {
        return "\n!!${user.fullName} does not have a $type address set up!!\n"
    }
}

@JvmOverloads
fun printLabelFor(user: User, type: AddressType = AddressType.Shipping) {
    println(labelFor(user, type))
}