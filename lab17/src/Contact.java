public record Contact(int id, String name, String phone, String email, String address) {
    public Contact {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Ten khong duoc de trong");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("So dien thoai khong duoc de trong");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email khong duoc de trong");
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Dia chi khong duoc de trong");
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Ten: %s, SDT: %s, Email: %s, Dia chi: %s",
                id(), name(), phone(), email(), address());
    }
}