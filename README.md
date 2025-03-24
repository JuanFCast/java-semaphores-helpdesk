# 💤 Problem: The Sleeping Monitor

This project is a Java concurrency simulation of the classic **"Sleeping Monitor"** problem, developed as an assignment for the **Operating Systems** course at **Universidad Icesi**.

The scenario models the interaction between a teaching assistant (monitor) and several students seeking help with their programming assignments. The simulation uses **threads** and **semaphores** to coordinate the actions of the monitor and the students, respecting space limitations and arrival order.

## 🧵 Problem Description
- The monitor's office only has one chair for visitors.
- There are **3 chairs in the hallway** where students can wait.
- If the monitor is busy, students wait in the hallway (if there's a free chair).
- If the monitor is asleep and a student arrives, the student wakes them up.
- If no chairs are available, the student goes back to the computer lab and returns later.
- The monitor assists students in the order they arrived.
- All threads alternate between programming and asking for help, using random sleep times to simulate real work.

## ⚙️ Technologies Used
- Java
- Threads
- Semaphores (`java.util.concurrent.Semaphore`)
- Simulation with `Thread.sleep()`

## 👨‍💻 Authors
- **Juan Felipe Castillo Gómez**
- **Juan Camilo Ramírez Tabares**

---

📚 *Academic project for the Operating Systems course – Universidad Icesi*
