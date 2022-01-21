package com.appstenx.appstenxweather.common.utils

import com.appstenx.appstenxweather.weather.model.Days


data class Node(val data: String) {
    var next: Node? = null

}

object CircularDays {
    private var head: Node? = null;
    private var tail: Node? = null;
    fun getCircularDays(): Node {
        add(Node(Days.Monday.name))
        add(Node(Days.Tuesday.name))
        add(Node(Days.Wednesday.name))
        add(Node(Days.Thursday.name))
        add(Node(Days.Friday.name))
        add(Node(Days.Saturday.name))
        add(Node(Days.Sunday.name))
        return head!!
    }

    fun add(newNode: Node) {
        //Checks if the list is empty.
        var newNodeTemp = newNode
        if (head == null) {
            //If list is empty, both head and tail would point to new node.
            head = newNodeTemp;
            tail = newNodeTemp;
            newNodeTemp.next = head;
        } else {
            //tail will point to new node.
            tail?.next = newNodeTemp;
            //New node will become new tail.
            tail = newNodeTemp;
            //Since, it is circular linked list tail will point to head.
            tail?.next = head;
        }
    }
}