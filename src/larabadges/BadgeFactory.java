package larabadges;

public interface BadgeFactory {
    boolean hasPage(int pageIndex);
    Page getPage(int pageIndex);
}
