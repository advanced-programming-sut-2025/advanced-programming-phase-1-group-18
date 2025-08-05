package io.github.group18.Model;

import io.github.group18.enums.QuestStatus;

public class NPCItem {
    int quantity;
    int requiredLevel;
    QuestStatus status;

    public NPCItem() {
        status = QuestStatus.DARYAFT_NASHODE;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }
}
