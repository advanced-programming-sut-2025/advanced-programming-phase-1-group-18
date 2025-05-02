package Model.Items;

import Model.App;
import Model.Cord;
import Model.Kashi;
import enums.AllCropsEnums;
import enums.ForagingSeedsEnums;
import enums.ForagingTreesEnums;
import enums.MixedSeedsEnums;

import java.util.ArrayList;
import java.util.List;

public class AllCrop extends Crop {
    private ForagingSeedsEnums sourceForagingSeedEnum;
    private MixedSeedsEnums sourceMixedSeedEnum;
    private AllCropsEnums type;
    private int daysGrowCounter;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private boolean canBecomeGiant;
    private boolean fedThisDay;
    private boolean edible;

    public void initilizeCrop(ForagingSeedsEnums foragingSeedsenums) {
        this.setSourceForagingSeedEnum(foragingSeedsenums);
        this.setDaysGrowCounter(0);
        this.setFedThisDay(false);
        switch (foragingSeedsenums) {
            case ForagingSeedsEnums.JazzSeeds: {
                this.setType(AllCropsEnums.BlueJazz);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(50);
                this.setEnergy(45);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.CarrotSeeds: {
                this.setType(AllCropsEnums.Carrot);
                this.setStages(new ArrayList<>(List.of(1, 1, 1)));
                this.setTotalHarvestTime(3);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(35);
                this.setEnergy(75);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.CauliflowerSeeds: {
                this.setType(AllCropsEnums.CauliFlower);
                this.setStages(new ArrayList<>(List.of(1, 2, 4, 4, 1)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(175);
                this.setEnergy(75);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.CoffeeBean: {
                this.setType(AllCropsEnums.CoffeeBean);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(10);
                this.setOneTime(false);
                this.setRegrowthTime(2);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(15);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.GarlicSeeds: {
                this.setType(AllCropsEnums.Garlic);
                this.setStages(new ArrayList<>(List.of(1, 1, 1, 1)));
                this.setTotalHarvestTime(4);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(60);
                this.setEnergy(20);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.BeanStarter: {
                this.setType(AllCropsEnums.GreenBean);
                this.setStages(new ArrayList<>(List.of(1, 1, 1, 3, 4)));
                this.setTotalHarvestTime(10);
                this.setOneTime(false);
                this.setRegrowthTime(3);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(40);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.KaleSeeds: {
                this.setType(AllCropsEnums.Kale);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 1)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(110);
                this.setEnergy(50);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.ParsnipSeeds: {
                this.setType(AllCropsEnums.Parsnip);
                this.setStages(new ArrayList<>(List.of(1, 1, 1, 1)));
                this.setTotalHarvestTime(4);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(35);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.PotatoSeeds: {
                this.setType(AllCropsEnums.Potato);
                this.setStages(new ArrayList<>(List.of(1, 1, 1, 2, 1)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.RhubarbSeeds: {
                this.setType(AllCropsEnums.Rhubarb);
                this.setStages(new ArrayList<>(List.of(2, 2, 2, 3, 4)));
                this.setTotalHarvestTime(13);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(220);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.StrawberrySeeds: {
                this.setType(AllCropsEnums.StrawBerry);
                this.setStages(new ArrayList<>(List.of(1, 1, 2, 2, 2)));
                this.setTotalHarvestTime(8);
                this.setOneTime(false);
                this.setRegrowthTime(4);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(120);
                this.setEnergy(50);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.TulipBulb: {
                this.setType(AllCropsEnums.Tulip);
                this.setStages(new ArrayList<>(List.of(1, 1, 2, 2)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(30);
                this.setEnergy(45);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.RiceShoot: {
                this.setType(AllCropsEnums.UnmilledRice);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(30);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.BlueberrySeeds: {
                this.setType(AllCropsEnums.BlueBerry);
                this.setStages(new ArrayList<>(List.of(1, 3, 3, 4, 2)));
                this.setTotalHarvestTime(13);
                this.setOneTime(false);
                this.setRegrowthTime(4);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(50);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.CornSeeds: {
                this.setType(AllCropsEnums.Corn);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 3)));
                this.setTotalHarvestTime(14);
                this.setOneTime(false);
                this.setRegrowthTime(4);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(50);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.HopsStarter: {
                this.setType(AllCropsEnums.Hops);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(11);
                this.setOneTime(false);
                this.setRegrowthTime(1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(25);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.PepperSeeds: {
                this.setType(AllCropsEnums.HotPepper);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(10);
                this.setOneTime(false);
                this.setRegrowthTime(3);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(40);
                this.setEnergy(5);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.MelonSeeds: {
                this.setType(AllCropsEnums.Melon);
                this.setStages(new ArrayList<>(List.of(2, 3, 3, 4, 3)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(250);
                this.setEnergy(113);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.PoppySeeds: {
                this.setType(AllCropsEnums.Poppy);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 1)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(140);
                this.setEnergy(15);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.RadishSeeds: {
                this.setType(AllCropsEnums.Radish);
                this.setStages(new ArrayList<>(List.of(1, 1, 2, 2)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(90);
                this.setEnergy(40);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.RedCabbageSeeds: {
                this.setType(AllCropsEnums.RedCabbage);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(9);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(260);
                this.setEnergy(75);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.StarfruitSeeds: {
                this.setType(AllCropsEnums.StarFruit);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 4)));
                this.setTotalHarvestTime(13);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(750);
                this.setEnergy(125);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.SpangleSeeds: {
                this.setType(AllCropsEnums.SummerSpangle);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(90);
                this.setEnergy(45);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.SummerSquashSeeds: {
                this.setType(AllCropsEnums.SummerSquash);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(125);
                this.setEnergy(60);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.SunflowerSeeds: {
                this.setType(AllCropsEnums.SunFlower);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.TomatoSeeds: {
                this.setType(AllCropsEnums.Tomato);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(11);
                this.setOneTime(false);
                this.setRegrowthTime(4);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(60);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.WheatSeeds: {
                this.setType(AllCropsEnums.Wheat);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 1)));
                this.setTotalHarvestTime(4);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(25);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.AmaranthSeeds: {
                this.setType(AllCropsEnums.Amaranth);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(150);
                this.setEnergy(50);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.ArtichokeSeeds: {
                this.setType(AllCropsEnums.Artichoke);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 3)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(160);
                this.setEnergy(65);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.BeetSeeds: {
                this.setType(AllCropsEnums.Beet);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 1)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(100);
                this.setEnergy(50);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.BokChoySeeds: {
                this.setType(AllCropsEnums.Bokchoy);
                this.setStages(new ArrayList<>(List.of(1, 1, 2, 2)));
                this.setTotalHarvestTime(4);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(35);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.BroccoliSeeds: {
                this.setType(AllCropsEnums.Broccoly);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(120);
                this.setEnergy(55);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.CranberrySeeds: {
                this.setType(AllCropsEnums.CramBerries);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(7);
                this.setOneTime(false);
                this.setRegrowthTime(5);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(75);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.EggplantSeeds: {
                this.setType(AllCropsEnums.EggPlant);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(5);
                this.setOneTime(false);
                this.setRegrowthTime(5);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(60);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.FairySeeds: {
                this.setType(AllCropsEnums.FairyRose);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(290);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.GrapeStarter: {
                this.setType(AllCropsEnums.Grape);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(10);
                this.setOneTime(false);
                this.setRegrowthTime(3);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(38);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.PumpkinSeeds: {
                this.setType(AllCropsEnums.Pumpkin);
                this.setStages(new ArrayList<>(List.of(2, 3, 3, 4, 3)));
                this.setTotalHarvestTime(13);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(320);
                this.setEnergy(125);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.YamSeeds: {
                this.setType(AllCropsEnums.Yam);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(10);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(160);
                this.setEnergy(60);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.RareSeed: {
                this.setType(AllCropsEnums.SweetGemBerry);
                this.setStages(new ArrayList<>(List.of(2, 3, 3, 3, 4)));
                this.setTotalHarvestTime(24);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(3000);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case ForagingSeedsEnums.PowdermelonSeeds: {
                this.setType(AllCropsEnums.PowderMelon);
                this.setStages(new ArrayList<>(List.of(1, 2, 3, 3, 3)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(500);
                this.setEnergy(110);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.AncientSeeds: {
                this.setType(AllCropsEnums.AncientFruit);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(28);
                this.setOneTime(false);
                this.setRegrowthTime(7);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(550);
                this.setEnergy(125);
                this.setEdible(true);
                break;
            }
            case ForagingSeedsEnums.MixedSeeds: {
                // No direct mapping for MixedSeeds
                break;
            }
        }
    }

    public void initilizeCrop(MixedSeedsEnums mixedSeedsenums) {
        this.setSourceMixedSeedEnum(mixedSeedsenums);
        this.setDaysGrowCounter(0);
        this.setFedThisDay(false);
        switch (mixedSeedsenums) {
            case MixedSeedsEnums.Artichoke: {
                this.setType(AllCropsEnums.Artichoke);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 3)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(160);
                this.setEnergy(65);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Corn: {
                this.setType(AllCropsEnums.Corn);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 3)));
                this.setTotalHarvestTime(14);
                this.setOneTime(false);
                this.setRegrowthTime(4);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(50);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Eggplant: {
                this.setType(AllCropsEnums.EggPlant);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 3, 2)));
                this.setTotalHarvestTime(5);
                this.setOneTime(false);
                this.setRegrowthTime(5);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(60);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Pumpkin: {
                this.setType(AllCropsEnums.Pumpkin);
                this.setStages(new ArrayList<>(List.of(2, 3, 3, 4, 3)));
                this.setTotalHarvestTime(13);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(320);
                this.setEnergy(125);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Sunflower: {
                this.setType(AllCropsEnums.SunFlower);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case MixedSeedsEnums.FairyRose: {
                this.setType(AllCropsEnums.FairyRose);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(290);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case MixedSeedsEnums.CauliFlower: {
                this.setType(AllCropsEnums.CauliFlower);
                this.setStages(new ArrayList<>(List.of(1, 2, 4, 4, 1)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(175);
                this.setEnergy(75);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Parsnip: {
                this.setType(AllCropsEnums.Parsnip);
                this.setStages(new ArrayList<>(List.of(1, 1, 1, 1)));
                this.setTotalHarvestTime(4);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(35);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Potato: {
                this.setType(AllCropsEnums.Potato);
                this.setStages(new ArrayList<>(List.of(1, 1, 1, 2, 1)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(80);
                this.setEnergy(25);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.BlueJazz: {
                this.setType(AllCropsEnums.BlueJazz);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(50);
                this.setEnergy(45);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Tulip: {
                this.setType(AllCropsEnums.Tulip);
                this.setStages(new ArrayList<>(List.of(1, 1, 2, 2)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(30);
                this.setEnergy(45);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.HotPepper: {
                this.setType(AllCropsEnums.HotPepper);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 2)));
                this.setTotalHarvestTime(10);
                this.setOneTime(false);
                this.setRegrowthTime(3);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(40);
                this.setEnergy(5);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Radish: {
                this.setType(AllCropsEnums.Radish);
                this.setStages(new ArrayList<>(List.of(1, 1, 2, 2)));
                this.setTotalHarvestTime(6);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(90);
                this.setEnergy(40);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.Wheat: {
                this.setType(AllCropsEnums.Wheat);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 1)));
                this.setTotalHarvestTime(4);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(25);
                this.setEnergy(0);
                this.setEdible(false);
                break;
            }
            case MixedSeedsEnums.Poppy: {
                this.setType(AllCropsEnums.Poppy);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2, 1)));
                this.setTotalHarvestTime(7);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(140);
                this.setEnergy(15);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.SummerSpangle: {
                this.setType(AllCropsEnums.SummerSpangle);
                this.setStages(new ArrayList<>(List.of(1, 2, 2, 2)));
                this.setTotalHarvestTime(8);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(false);
                this.setBaseSellPrice(90);
                this.setEnergy(45);
                this.setEdible(true);
                break;
            }
            case MixedSeedsEnums.PowderMelon: {
                this.setType(AllCropsEnums.PowderMelon);
                this.setStages(new ArrayList<>(List.of(1, 2, 3, 3, 3)));
                this.setTotalHarvestTime(12);
                this.setOneTime(true);
                this.setRegrowthTime(-1);
                this.setCanBecomeGiant(true);
                this.setBaseSellPrice(500);
                this.setEnergy(110);
                this.setEdible(true);
                break;
            }
        }
    }

    public ForagingSeedsEnums getSourceForagingSeedEnum() {
        return sourceForagingSeedEnum;
    }

    public void setSourceForagingSeedEnum(ForagingSeedsEnums sourceForagingSeedEnum) {
        this.sourceForagingSeedEnum = sourceForagingSeedEnum;
    }

    public MixedSeedsEnums getSourceMixedSeedEnum() {
        return sourceMixedSeedEnum;
    }

    public void setSourceMixedSeedEnum(MixedSeedsEnums sourceMixedSeedEnum) {
        this.sourceMixedSeedEnum = sourceMixedSeedEnum;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public boolean isFedThisDay() {
        return fedThisDay;
    }

    public void setFedThisDay(boolean fedThisDay) {
        this.fedThisDay = fedThisDay;
    }

    public AllCropsEnums getType() {
        return type;
    }

    public void setType(AllCropsEnums tp) {
        this.type = tp;
    }

    public int getDaysGrowCounter() {
        return daysGrowCounter;
    }

    public void setDaysGrowCounter(int daysGrowCounter) {
        this.daysGrowCounter = daysGrowCounter;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Integer> stages) {
        this.stages = stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public void setRegrowthTime(int regrowthTime) {
        this.regrowthTime = regrowthTime;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(true);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getAllCrops().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
    }

}
