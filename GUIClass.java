import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.*;
import javax.swing.*;
import javafx.scene.*;
import javafx.stage.*;

public class GUIClass extends Application {
    protected AddressBookPane pane = new AddressBookPane();
    final int nameSize = 32;
    final int streetSize = 32;
    final int citySize = 20;
    final int stateSize = 2;
    final int zipSize = 5;
    protected int count = 0;
    //constructor
    public GUIClass() {}
    //Define stage()
    public void start(Stage stg) {
        Scene scn = new Scene(this.pane, 1000.0D, 150.0D);
        stg.setScene(scn);
        stg.setTitle("Address Book");
        stg.show();

        this.pane.addButton.setOnAction((var1x) -> {
            this.add();
        });
        this.pane.firstButton.setOnAction((var1x) -> {
            this.first();
        });
        this.pane.nextButton.setOnAction((var1x) -> {
            this.next();
        });
        this.pane.prevButton.setOnAction((var1x) -> {
            this.previous();
        });
        this.pane.lastButton.setOnAction((var1x) -> {
            this.last();
        });
        this.pane.updateButton.setOnAction((var1x) -> {
            this.update();
        });
    }

    //Define Write method
    //Only write the given size for the string
    private void write(RandomAccessFile AddressBook) throws IOException {
        AddressBook.write(this.fixedLength(this.pane.personName.getText().getBytes(), 32));
        AddressBook.write(this.fixedLength(this.pane.streetName.getText().getBytes(), 32));
        AddressBook.write(this.fixedLength(this.pane.cityName.getText().getBytes(), 20));
        AddressBook.write(this.fixedLength(this.pane.stateName.getText().getBytes(), 2));
        AddressBook.write(this.fixedLength(this.pane.zipCode.getText().getBytes(), 5));
        System.out.println("Address #" + this.count + " saved!");
    }

    //Define Read method
    private void read(RandomAccessFile AddressBook) throws IOException {
        byte[] bytName = new byte[nameSize];
        int bytB = AddressBook.read(bytName);
        this.pane.personName.setText(new String(bytName));
        byte[] bytStreet = new byte[streetSize];
        bytB += AddressBook.read(bytStreet);
        this.pane.streetName.setText(new String(bytStreet));
        byte[] bytCity = new byte[citySize];
        bytB += AddressBook.read(bytCity);
        this.pane.cityName.setText(new String(bytCity));
        byte[] bytState = new byte[stateSize];
        bytB += AddressBook.read(bytState);
        this.pane.stateName.setText(new String(bytState));
        byte[] bytZip = new byte[zipSize];
        int var10000 = bytB + AddressBook.read(bytZip);
        this.pane.zipCode.setText(new String(bytZip));
    }

    //Implement add() and call write()
    private void add() {
        try {
            RandomAccessFile AddressBook = new RandomAccessFile("AddressBook.dat", "rw");
            Throwable var2 = null;

            try {
                AddressBook.seek(AddressBook.length());
                this.write(AddressBook);
                JOptionPane.showMessageDialog(null, "Saved Successfully!");
            } catch (Throwable var14) {
                var2 = var14;
                throw var14;
            } finally {
                if (AddressBook != null) {
                    if (var2 != null) {
                        try {
                            AddressBook.close();

                        } catch (Throwable var13) {
                            var2.addSuppressed(var13);
                            //JOptionPane.showMessageDialog(null, "Error occurred during saving!");
                        }
                    } else {

                        AddressBook.close();
                    }
                }
            }
        } catch (FileNotFoundException var16) {
            System.out.println("File not found exception in add()!");
        } catch (IOException var17) {
            System.out.println("IO exception in add()!");
        } catch (IndexOutOfBoundsException var18) {
            System.out.println("Index out of bound exception in add()!");;
        }
    }

    //Implement first() and call read()
    private void first() {
        try {
            RandomAccessFile addressBook = new RandomAccessFile("AddressBook.dat", "rw");
            Throwable var2 = null;

            try {
                if (addressBook.length() > 0L) {
                    addressBook.seek(0L);
                    this.read(addressBook);
                    System.out.println("Reading address #1");
                    this.count = 1;
                } else {
                    System.out.println("Address is empty!");
                }
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (addressBook != null) {
                    if (var2 != null) {
                        try {
                            addressBook.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        addressBook.close();
                    }
                }
            }
        } catch (IOException var14) {
            System.out.println("IO exception in first()!");
        }
    }

    //Implement next() and call read()
    private void next() {
        try {
            RandomAccessFile addressBook = new RandomAccessFile("AddressBook.dat", "rw");
            Throwable var2 = null;

            try {
                if ((long)(this.count * 91) < addressBook.length()) {
                    addressBook.seek((long)(this.count * 91));
                    this.read(addressBook);
                    ++this.count;
                    System.out.println("Reading address #" + this.count);
                } else {
                    System.out.println("End of file!");
                }
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (addressBook != null) {
                    if (var2 != null) {
                        try {
                            addressBook.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        addressBook.close();
                    }
                }
            }
        } catch (IOException var14) {
            System.out.println("IO exception in next()!");
        }

    }

    //Implement previous() and call read()
    private void previous() {
        try {
            RandomAccessFile addressBook = new RandomAccessFile("AddressBook.dat", "rw");
            Throwable var2 = null;

            try {
                if (this.count > 1) {
                    --this.count;
                } else {
                    this.count = 1;
                }

                addressBook.seek((long)(this.count * 91 - 91));
                this.read(addressBook);
                System.out.println("Reading address #" + this.count);
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (addressBook != null) {
                    if (var2 != null) {
                        try {
                            addressBook.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        addressBook.close();
                    }
                }
            }
        } catch (IOException var14) {
            System.out.println("IO exception in previous()!");
        }

    }

    //Implement last() and call read()
    private void last() {
        try {
            RandomAccessFile addressBook = new RandomAccessFile("AddressBook.dat", "rw");
            Throwable var2 = null;

            try {
                this.count = (int)addressBook.length() / 91;
                addressBook.seek((long)(this.count * 91 - 91));
                this.read(addressBook);
                System.out.println("Reading address #" + this.count);
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (addressBook != null) {
                    if (var2 != null) {
                        try {
                            addressBook.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        addressBook.close();
                    }
                }
            }
        } catch (IOException var14) {
            System.out.println("IO exception in last()!");
        }

    }

    //Implement update() and call write()
    private void update() {
        try {
            RandomAccessFile addressBook = new RandomAccessFile("AddressBook.dat", "rw");
            Throwable var2 = null;

            try {
                addressBook.seek((long)(this.count * 91 - 91));
                this.write(addressBook);
            } catch (Throwable var13) {
                var2 = var13;
                throw var13;
            } finally {
                if (addressBook != null) {
                    if (var2 != null) {
                        try {
                            addressBook.close();
                        } catch (Throwable var12) {
                            var2.addSuppressed(var12);
                        }
                    } else {
                        addressBook.close();
                    }
                }
            }
        } catch (FileNotFoundException var15) {
            System.out.println("File not found exception in update()!");
        } catch (IOException var16) {
            System.out.println("IO exception in update()!");
        }

    }

    //Get fixed Size of String
    private byte[] fixedLength(byte[] data, int size) {
        byte[] tempData = new byte[size];
        for(int i = 0; i < data.length; i++) {
            tempData[i] = data[i];
        }
        return tempData;
    }
}
