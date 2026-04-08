# Java Cyber-Toolkit

A high-performance, multi-threaded networking and security utility built with Java 21.

## Features

- **TCP Port Scanner**: Leverages Java 21 Virtual Threads (Project Loom) for massive concurrency and speed.
- **Subnet Discovery**: CIDR-based host discovery and network range scanning.
- **Cipher Tools**: Integrated cryptographic module featuring XOR encryption with Hex output.
- **Fail-Fast Validation**: Advanced DNS and TCP probing to ensure targets are reachable before scanning.
- **Thread-Safe Logging**: Synchronized event logging for accurate scan results.

## Technology Stack

- **Language:** Java 21 (Loom, Enhanced Switches, Virtual Threads)
- **Build Tool:** Maven
- **Concurrency:** `Executors.newVirtualThreadPerTaskExecutor()`

## Quick Start

### Prerequisites

- JDK 21+
- Maven 3.9+

### Build
```
mvn clean package
```
### Run
```
java -jar target/toolkit-1.0-SNAPSHOT.jar
```

## Legal Disclaimer

This tool is developed for educational purposes and authorized security testing only. The author is not responsible for any misuse or damage caused by this application.

Built during **Hack Club Lock In 2026**.
