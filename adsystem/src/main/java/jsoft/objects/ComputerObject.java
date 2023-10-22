package jsoft.objects;

public class ComputerObject {
    private int computer_id;
    private String computer_product_code;
    private byte computer_manufacture;
    private String computer_model;
    private byte computer_os;
    private String computer_cpu;
    private double computer_cpu_speed;
    private byte computer_cache;
    private String computer_chipset;
    private short computer_graphic_memory;
    private String computer_card_interface;
    private String computer_card_network;

    // chung quy RAM hien nay >= 1GB
    private byte computer_ram;
    private byte computer_max_ram;
    private byte computer_ram_category;
    private short computer_bus;

    // wifi/card_sound, fdd, monitor -> manufacturer name
    private String computer_wifi;
    private String computer_card_sound;
    private byte computer_sound_category;
    private boolean computer_hdd;
    private byte computer_hdd_interface;
    // chi co mot vai chuan giao thuc
    private String computer_fdd;
    private String computer_monitor;
    // monitor manufacturer name?
    private byte computer_monitor_interface;
    private byte computer_monitor_resolution;
    private byte computer_monitor_category;
    private byte computer_monitor_size;
    private double computer_power;
    // battery

}
