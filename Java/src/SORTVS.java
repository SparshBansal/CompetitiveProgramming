import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SORTVS {

    private static final FastReader fastReader = new FastReader();

    private static class Tuple<L, R> {
        L _1;
        R _2;

        Tuple(L _1, R _2) {
            this._1 = _1;
            this._2 = _2;
        }

        public L _1() {
            return _1;
        }

        public R _2() {
            return _2;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Tuple)) {
                return false;
            }
            Tuple other = (Tuple) obj;
            if (this == other) {
                return true;
            }
            return this._1().equals(other._1()) && this._2().equals(other._2());
        }
    }

    private static class ShuffleSet {
        private Set<Integer> positions;
        private Set<Vase> childVases;

        public ShuffleSet() {
            this(Collections.EMPTY_SET);
        }

        ShuffleSet(Integer position) {
            this(new HashSet<>(Collections.singletonList(position)));
        }

        ShuffleSet(Collection<Integer> positions) {
            this.positions = new HashSet<>(positions);
            this.childVases = new HashSet<>();
        }

        public long getSize() {
            return this.positions.size();
        }

        public Set<Integer> getPositions() {
            return positions;
        }

        public void setPositions(Set<Integer> positions) {
            this.positions = positions;
        }

        public Set<Vase> getChildVases() {
            return childVases;
        }

        public void setChildVases(Set<Vase> childVases) {
            this.childVases = childVases;
        }

        public void addVase(Vase vase) {
            this.childVases.add(vase);
            vase.setParentSet(this);
        }

        public void removeVase(Vase vase) {
            this.childVases.remove(vase);
            vase.setParentSet(null);
        }

        public void merge(ShuffleSet other) {
            this.positions.addAll(other.getPositions());
            this.childVases.addAll(other.getChildVases());
        }


        public Set<Vase> getNonBelongingVases() {
            return this.getChildVases().stream()
                    .filter(vase -> !this.getPositions().contains(vase.getRequiredPosition()))
                    .collect(Collectors.toSet());
        }


        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ShuffleSet)) {
                return false;
            }
            ShuffleSet other = (ShuffleSet) obj;
            if (this == other) {
                return true;
            }
            return this.positions.equals(other.getPositions()) &&
                    this.childVases.equals(other.getChildVases());
        }
    }

    private static class Vase {
        int currentPosition;
        int requiredPosition;
        ShuffleSet parentSet;


        Vase(int currentPosition, int requiredPosition) {
            this.currentPosition = currentPosition;
            this.requiredPosition = requiredPosition;
        }

        public int getCurrentPosition() {
            return currentPosition;
        }

        public void setCurrentPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        public int getRequiredPosition() {
            return requiredPosition;
        }

        public void setRequiredPosition(int requiredPosition) {
            this.requiredPosition = requiredPosition;
        }

        public ShuffleSet getParentSet() {
            return parentSet;
        }

        public void setParentSet(ShuffleSet parentSet) {
            this.parentSet = parentSet;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vase)) {
                return false;
            }
            Vase other = (Vase) obj;
            if (this == other) {
                return true;
            }
            return this.requiredPosition == other.requiredPosition &&
                    this.currentPosition == other.currentPosition;
        }
    }

    public static void main(String[] args) {
        int t = fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n = fastReader.nextInt();
            int m = fastReader.nextInt();

            int[] permutation = new int[n];
            for (int j = 0; j < n; j++) {
                permutation[j] = fastReader.nextInt();
            }


            int[] parentIdx = new int[n];
            ShuffleSet[] parents = new ShuffleSet[n];
            createShuffleSets(n, m, parentIdx, parents);

            // map permutations to vases
            IntStream.range(0, n).mapToObj(idx -> new Vase(idx + 1, n - permutation[idx] + 1))
                    .forEach(vase -> parents[parentIdx[vase.getCurrentPosition() - 1]].addVase(vase));

            // collect all parent shuffle sets
            Set<ShuffleSet> allSets = Arrays.stream(parentIdx).mapToObj(idx -> parents[idx])
                    .collect(Collectors.toSet());

            assert allSets.stream().mapToLong(ss -> ss.getChildVases().size()).sum() == n;

            if (allSets.size() == 1) {
                // already we can compute in 0 moves , print 0 and get out
                System.out.println(0);
                continue;
            }


            int totalCount = 0;
            // now solve for the other case
            for (int q = 0; q < n; q++) {
                // get candidates
                List<Vase> candidates = allSets.stream()
                        .flatMap(shuffleSet -> shuffleSet.getNonBelongingVases().stream())
                        .collect(Collectors.toList());

                Optional<Tuple<Vase, Vase>> safeSwap = getSafeSwaps(candidates);
                if (safeSwap.isPresent()) {
                    // swap
                    swap(safeSwap.get()._1(), safeSwap.get()._2());
                    totalCount++;
                } else {
                    // we need to find safe generating unsafe swaps
                    Optional<Tuple<Vase, Vase>> safeGenertingUnsafeSwap = getSafeGeneratingUnsafeSwap(candidates);
                    if (safeGenertingUnsafeSwap.isPresent()) {
                        totalCount++;
                        swap(safeGenertingUnsafeSwap.get()._1(), safeGenertingUnsafeSwap.get()._2());
                    }
                    else {
                        // just make the first unsafe swap
                        Optional<Tuple<Vase, Vase>> unsafeSwap = getUnsafeSwap(candidates);
                        if (unsafeSwap.isPresent()) {
                            totalCount++;
                            swap(unsafeSwap.get()._1(), unsafeSwap.get()._2());
                        }
                    }
                }
            }

            System.out.println(totalCount);
        }
    }

    private static Optional<Tuple<Vase, Vase>> getUnsafeSwap(List<Vase> candidates) {
        Vase[] arr = new Vase[candidates.size()];
        candidates.toArray(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                Vase _1 = arr[i];
                Vase _2 = arr[j];
                return Optional.of(new Tuple<Vase, Vase>(_1, _2));
            }
        }
        return Optional.empty();
    }

    private static void swap(Vase a, Vase b) {
        ShuffleSet parentOfA = a.getParentSet();
        ShuffleSet parentOfB = b.getParentSet();

        parentOfA.removeVase(a);
        parentOfB.removeVase(b);

        parentOfB.addVase(a);
        parentOfA.addVase(b);
    }


    private static Optional<Tuple<Vase, Vase>> getSafeGeneratingUnsafeSwap(List<Vase> candidates) {
        Vase[] arr = new Vase[candidates.size()];
        candidates.toArray(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                Vase _1 = arr[i];
                Vase _2 = arr[j];

                // find the beneficial vase
                if (isGoodToSwap(_1, _2) && willGenerateSafeSwap(_1, _2, candidates)) {
                    return Optional.of(new Tuple<>(_1, _2));
                }
            }
        }
        return Optional.empty();
    }

    private static boolean willGenerateSafeSwap(Vase a, Vase b, List<Vase> candidates) {
        // find benefitting and non benefitting vase from the swap
        Tuple<Vase, Vase> benefittingPair = getBenefittingVase(a, b);
        for (Vase other : candidates) {
            // check if there can be a safe swap that can be generated from this unsafe swap
            if (other != benefittingPair._1() && other != benefittingPair._2()) {
                // check if safe swap will be generate
                if (isSafeSwap(benefittingPair._2(), other.getParentSet(), other, benefittingPair._1().getParentSet())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Tuple<Vase, Vase> getBenefittingVase(Vase a, Vase b) {
        if (b.getParentSet().getPositions().contains(a.getRequiredPosition())) {
            return new Tuple<>(a, b);
        } else {
            return new Tuple<>(b, a);
        }
    }

    private static boolean isGoodToSwap(Vase a, Vase b) {
        return b.getParentSet().getPositions().contains(a.getRequiredPosition()) ||
                a.getParentSet().getPositions().contains(b.getRequiredPosition());
    }

    private static Optional<Tuple<Vase, Vase>> getSafeSwaps(List<Vase> candidates) {
        Vase[] arr = new Vase[candidates.size()];
        candidates.toArray(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                Vase _1 = arr[i];
                Vase _2 = arr[j];

                if (isSafeSwap(_1, _2)) {
                    return Optional.of(new Tuple<>(_1, _2));
                }
            }
        }
        return Optional.empty();
    }

    private static boolean isSafeSwap(Vase a, Vase b) {
        return isSafeSwap(a, b.getParentSet(), b, a.getParentSet());
    }

    private static boolean isSafeSwap(Vase a, ShuffleSet aDest, Vase b, ShuffleSet bDest) {
        return bDest.getPositions().contains(b.getRequiredPosition()) &&
                aDest.getPositions().contains(a.getRequiredPosition());
    }

    private static void createShuffleSets(int n, int m, int[] parentIdx, ShuffleSet[] parents) {
        IntStream.range(0, n).forEach(idx -> {
            parents[idx] = new ShuffleSet(idx + 1);
            parentIdx[idx] = idx;
        });

        for (int j = 0; j < m; j++) {
            int a = fastReader.nextInt() - 1;
            int b = fastReader.nextInt() - 1;

            int aIdx = getParentIdx(a, parentIdx);
            int bIdx = getParentIdx(b, parentIdx);
            if (aIdx != bIdx) {
                // merge the shuffle sets
                if (parents[aIdx].getSize() < parents[bIdx].getSize()) {
                    // merge set a and b
                    parents[bIdx].merge(parents[aIdx]);
                    parentIdx[aIdx] = bIdx;
                } else {
                    // merge set a and b
                    parents[aIdx].merge(parents[bIdx]);
                    parentIdx[bIdx] = aIdx;
                }
            }
        }
    }

    private static int getParentIdx(int a, int[] parentIdx) {
        while (parentIdx[a] != a) {
            a = parentIdx[a];
        }
        return a;
    }


    static class FastReader {

        Scanner scanner;
        public FastReader() {
            scanner = new Scanner(System.in);
        }

        int nextInt() {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            return 0;
        }
    }
}
