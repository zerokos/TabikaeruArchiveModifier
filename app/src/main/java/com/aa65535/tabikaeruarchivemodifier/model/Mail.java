package com.aa65535.tabikaeruarchivemodifier.model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Mail extends Data<Void> {
    private int id;
    private Type type;
    private Str title;
    private Str message;
    private Int clover;
    private Int ticket;
    private Item item;
    private DateTime datetime;
    private Bool opened;
    private Bool protect;

    public static class Type extends Int {
        public static final Type NONE = new Type(-1);
        public static final Type MESSAGE = new Type(0);
        public static final Type PICTURE = new Type(1);
        public static final Type GIFT = new Type(2);
        public static final Type MANAGEMENT = new Type(3);
        public static final Type LEAFLET = new Type(4);

        Type(int v) {
            super(v);
        }

        Type(RandomAccessFile r) throws IOException {
            super(r);
        }

        @Override
        public String toString() {
            switch (value()) {
                case 0:
                    return "MESSAGE";
                case 1:
                    return "PICTURE";
                case 2:
                    return "GIFT";
                case 3:
                    return "MANAGEMENT";
                case 4:
                    return "LEAFLET";
            }
            return "NONE";
        }
    }

    Mail(RandomAccessFile r) throws IOException {
        super(r, null);
    }

    @Override
    protected void initialize(Void arg) throws IOException {
        this.title = new Str(r, 0x28);
        this.message = new Str(r, 0x28);
        r.skipBytes(0x04); // id, skipped
        r.skipBytes(0x04); // sender chara id, skipped
        this.type = new Type(r);
        this.clover = new Int(r);
        this.ticket = new Int(r);
        this.item = new Item(r);
        this.id = r.readInt();
        this.datetime = new DateTime(r);
        this.opened = new Bool(r);
        this.protect = new Bool(r);
    }

    public int id() {
        return id;
    }

    public Type type() {
        return type;
    }

    public Str title() {
        return title;
    }

    public Str message() {
        return message;
    }

    public Int clover() {
        return clover;
    }

    public Int ticket() {
        return ticket;
    }

    public Item item() {
        return item;
    }

    public DateTime datetime() {
        return datetime;
    }

    public Bool opened() {
        return opened;
    }

    public Bool protect() {
        return protect;
    }

    public Mail type(Type type) {
        this.type.value(type.value());
        return this;
    }

    public Mail title(String title) {
        this.title.value(title);
        return this;
    }

    public Mail message(String message) {
        this.message.value(message);
        return this;
    }

    public Mail clover(int clover) {
        this.clover.value(clover);
        return this;
    }

    public Mail ticket(int ticket) {
        this.ticket.value(ticket);
        return this;
    }

    public Mail opened(boolean opened) {
        this.opened.value(opened);
        return this;
    }

    public Mail protect(boolean protect) {
        this.protect.value(protect);
        return this;
    }

    @Override
    public boolean save() {
        return type.save() && title.save() && message.save() &&
                clover.save() && ticket.save() && item.save() &&
                datetime.save() && opened.save() && protect.save();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Mail mail = (Mail) o;
        return id == mail.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "offset=" + offset +
                ", length=" + length +
                ", id=" + id +
                ", type=" + type +
                ", title=" + title +
                ", message=" + message +
                ", clover=" + clover +
                ", ticket=" + ticket +
                ", item=" + item +
                ", datetime=" + datetime +
                ", opened=" + opened +
                ", protect=" + protect +
                '}';
    }
}
