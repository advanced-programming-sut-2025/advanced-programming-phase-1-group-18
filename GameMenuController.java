package Controller;

import Model.App;
import Model.Result;

public class GameMenuController implements ShowCurrentMenu{
    public void exit() {

    }

    public Result gameNew(String command) {
        return new Result(true, "");
    }

    public Result gameMap(int mapNumber) {
        return new Result(true, "");
    }

    public Result exitGame() {
        return new Result(true, "");
    }

    public Result loadGame() {
        return new Result(true, "");
    }

    public Result saveGame() {
        return new Result(true, "");
    }

    public Result removeGame() {
        return new Result(true, "");
    }

    public Result nextTurn() {
        return new Result(true, "");
    }


    public void time() {

    }

    public void date() {

    }

    public void dateTime() {

    }

    public Result cheatAdvanceTime(int hour) {
        return new Result(true, "");
    }

    public Result cheatAdvanceDate(int hour) {
        return new Result(true, "");
    }

    public void season() {

    }

    public Result cheatThor(int x, int y) {
        return new Result(true, "");
    }

    public void weather() {

    }

    public void weatherForecast() {

    }

    public Result cheatWeather(String Type) {
        return new Result(true, "");
    }

    public Result greenHouseBiuld() {
        return new Result(true, "");
    }

    public Result walk(int x, int y) {
        return new Result(true, "");
    }

    public void printMap(int x, int y, int size) {

    }

    public void helpReadingMap() {

    }

    public void energyShow() {

    }

    public Result energySet(int value) {
        return new Result(true, "");
    }

    public void energyUnlimited() {

    }

    public void inventoryShow() {

    }

    public Result inventoryTrash(String name, int number) {
        return new Result(true, "");
    }

    public Result toolEquip(String toolName) {
        return new Result(true, "");
    }

    public void toolsShowCurrent() {

    }

    public void toolShowAvailable() {

    }

    public Result toolsUpgrade(String toolName) {
        return new Result(true, "");
    }

    public Result toolsUse(String direction) {
        return new Result(true, "");
    }

    public Result craftInfo(String carftName) {
        return new Result(true, "");
    }

    public Result plant(String seed, String direction) {
        return new Result(true, "");
    }

    public Result showPlant(int x, int y) {
        return new Result(true, "");
    }

    public Result fertilize(String fertilizer, String direction) {
        return new Result(true, "");
    }

    public void howMuchWater() {

    }

    public void craftingShowRecipes() {

    }

    public Result craftingCraft(String name) {
        return new Result(true, "");
    }

    public Result placeItem(String name, String direction) {
        return new Result(true, "");
    }

    public Result cheatAddItem(String name, int count) {
        return new Result(true, "");
    }

    public Result cookingRefrigerator(String pickOrPut, String item) {
        return new Result(true, "");
    }

    public void cookingShowRecipes() {

    }

    public Result cookingPrepare(String recipeName) {
        return new Result(true, "");
    }

    public Result eat(String foodName) {
        return new Result(true, "");
    }

    public Result build(String buildingName, int x, int y) {
        return new Result(true, "");
    }

    public Result buyAnimal(String animal, String name) {
        return new Result(true, "");
    }

    public Result pet(String name) {
        return new Result(true, "");
    }

    public Result cheatSetFriendship(String animalName, int amount) {
        return new Result(true, "");
    }

    public void animals() {

    }

    public Result shepherdAnimals(String animalName, int x, int y) {
        return new Result(true, "");
    }

    public Result feedHay(String animalName) {
        return new Result(true, "");
    }

    public void produces() {

    }

    public Result collectProduce(String name) {
        return new Result(true, "");
    }

    public Result sellAnimal(String name) {
        return new Result(true, "");
    }

    public Result fishing(String fishingPole) {
        return new Result(true, "");
    }

    public Result artisanUse(String artisanName, String item1Name) {
        return new Result(true, "");
    }

    public Result artisanGet(String artisanName) {
        return new Result(true, "");
    }

    public Result cheatAdd(int count)
    {
        return new Result(true, "");
    }
    public Result sell(String productName, int count)
    {
        return new Result(true, "");
    }
    public void friendships()
    {

    }
    public Result talk(String username, String message)
    {
        return new Result(true, "");
    }
    public Result talkHistory(String username)
    {
        return new Result(true, "");
    }
    public Result gift(String userName,int amount,String item)
    {
        return new Result(true, "");
    }
    public  void  giftList()
    {

    }
    public Result giftRate(int giftNumber,int rate)
    {
        return new Result(true, "");
    }
    public Result giftHistory(String name)
    {
        return new Result(true, "");
    }
    public Result hug(String userName)
    {
        return new Result(true, "");
    }
    public Result flower(String userName)
    {
        return new Result(true, "");
    }
    public Result askMarriage(String userName,String ring)
    {
        return new Result(true, "");
    }
    public Result response(String acceptOrReject,String userName)
    {
        return new Result(true, "");
    }
    public void startTrade()
    {

    }




}
