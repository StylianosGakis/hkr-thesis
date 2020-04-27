package se.stylianosgakis.benchmarkapplication.benchmark.reversecomplement

private const val FASTA_INPUT =
    ">ONE Homo sapiens alu\nGGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\nTCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\nAAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAG\nGCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\nCCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAGGCCGGGCGCGGT\nGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGATCACCTGAGGTCA\nGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAATACAAAAA\nTTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAGGCTGAGGCAGGAG\nAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACTGCACTCCA\nGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAGGCCGGGCGCGGTGGCTCACGCCTGT\nAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGATCACCTGAGGTCAGGAGTTCGAGACC\nAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAATACAAAAATTAGCCGGGCGTG\nGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAGGCTGAGGCAGGAGAATCGCTTGAACC\nCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACTGCACTCCAGCCTGGGCGACAG\nAGCGAGACTCCGTCTCAAAAAGGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTT\nTGGGAGGCCGAGGCGGGCGGATCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACA\nTGGTGAAACCCCGTCTCTACTAAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCT\nGTAATCCCAGCTACTCGGGAGGCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGG\nTTGCAGTGAGCCGAGATCGCGCCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGT\nCTCAAAAAGGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGG\nCGGGCGGATCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCG\nTCTCTACTAAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTA\nCTCGGGAGGCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCG\nAGATCGCGCCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAGGCCG\nGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGATCACC\nTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAA\nTACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAGGCTGA\nGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACT\nGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAGGCCGGGCGCGGTGGCTC\nACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGATCACCTGAGGTCAGGAGT\nTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACTAAAAATACAAAAATTAGC\nCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAGGCTGAGGCAGGAGAATCG\nCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCGCCACTGCACTCCAGCCTG\nGGCGACAGAGCGAGACTCCG\n>TWO IUB ambiguity codes\ncttBtatcatatgctaKggNcataaaSatgtaaaDcDRtBggDtctttataattcBgtcg\ntactDtDagcctatttSVHtHttKtgtHMaSattgWaHKHttttagacatWatgtRgaaa\nNtactMcSMtYtcMgRtacttctWBacgaaatatagScDtttgaagacacatagtVgYgt\ncattHWtMMWcStgttaggKtSgaYaaccWStcgBttgcgaMttBYatcWtgacaYcaga\ngtaBDtRacttttcWatMttDBcatWtatcttactaBgaYtcttgttttttttYaaScYa\nHgtgttNtSatcMtcVaaaStccRcctDaataataStcYtRDSaMtDttgttSagtRRca\ntttHatSttMtWgtcgtatSSagactYaaattcaMtWatttaSgYttaRgKaRtccactt\ntattRggaMcDaWaWagttttgacatgttctacaaaRaatataataaMttcgDacgaSSt\nacaStYRctVaNMtMgtaggcKatcttttattaaaaagVWaHKYagtttttatttaacct\ntacgtVtcVaattVMBcttaMtttaStgacttagattWWacVtgWYagWVRctDattBYt\ngtttaagaagattattgacVatMaacattVctgtBSgaVtgWWggaKHaatKWcBScSWa\naccRVacacaaactaccScattRatatKVtactatatttHttaagtttSKtRtacaaagt\nRDttcaaaaWgcacatWaDgtDKacgaacaattacaRNWaatHtttStgttattaaMtgt\ntgDcgtMgcatBtgcttcgcgaDWgagctgcgaggggVtaaScNatttacttaatgacag\ncccccacatYScaMgtaggtYaNgttctgaMaacNaMRaacaaacaKctacatagYWctg\nttWaaataaaataRattagHacacaagcgKatacBttRttaagtatttccgatctHSaat\nactcNttMaagtattMtgRtgaMgcataatHcMtaBSaRattagttgatHtMttaaKagg\nYtaaBataSaVatactWtataVWgKgttaaaacagtgcgRatatacatVtHRtVYataSa\nKtWaStVcNKHKttactatccctcatgWHatWaRcttactaggatctataDtDHBttata\naaaHgtacVtagaYttYaKcctattcttcttaataNDaaggaaaDYgcggctaaWSctBa\naNtgctggMBaKctaMVKagBaactaWaDaMaccYVtNtaHtVWtKgRtcaaNtYaNacg\ngtttNattgVtttctgtBaWgtaattcaagtcaVWtactNggattctttaYtaaagccgc\ntcttagHVggaYtgtNcDaVagctctctKgacgtatagYcctRYHDtgBattDaaDgccK\ntcHaaStttMcctagtattgcRgWBaVatHaaaataYtgtttagMDMRtaataaggatMt\nttctWgtNtgtgaaaaMaatatRtttMtDgHHtgtcattttcWattRSHcVagaagtacg\nggtaKVattKYagactNaatgtttgKMMgYNtcccgSKttctaStatatNVataYHgtNa\nBKRgNacaactgatttcctttaNcgatttctctataScaHtataRagtcRVttacDSDtt\naRtSatacHgtSKacYagttMHtWataggatgactNtatSaNctataVtttRNKtgRacc\ntttYtatgttactttttcctttaaacatacaHactMacacggtWataMtBVacRaSaatc\ncgtaBVttccagccBcttaRKtgtgcctttttRtgtcagcRttKtaaacKtaaatctcac\naattgcaNtSBaaccgggttattaaBcKatDagttactcttcattVtttHaaggctKKga\ntacatcBggScagtVcacattttgaHaDSgHatRMaHWggtatatRgccDttcgtatcga\naacaHtaagttaRatgaVacttagattVKtaaYttaaatcaNatccRttRRaMScNaaaD\ngttVHWgtcHaaHgacVaWtgttScactaagSgttatcttagggDtaccagWattWtRtg\nttHWHacgattBtgVcaYatcggttgagKcWtKKcaVtgaYgWctgYggVctgtHgaNcV\ntaBtWaaYatcDRaaRtSctgaHaYRttagatMatgcatttNattaDttaattgttctaa\nccctcccctagaWBtttHtBccttagaVaatMcBHagaVcWcagBVttcBtaYMccagat\ngaaaaHctctaacgttagNWRtcggattNatcRaNHttcagtKttttgWatWttcSaNgg\ngaWtactKKMaacatKatacNattgctWtatctaVgagctatgtRaHtYcWcttagccaa\ntYttWttaWSSttaHcaaaaagVacVgtaVaRMgattaVcDactttcHHggHRtgNcctt\ntYatcatKgctcctctatVcaaaaKaaaagtatatctgMtWtaaaacaStttMtcgactt\ntaSatcgDataaactaaacaagtaaVctaggaSccaatMVtaaSKNVattttgHccatca\ncBVctgcaVatVttRtactgtVcaattHgtaaattaaattttYtatattaaRSgYtgBag\naHSBDgtagcacRHtYcBgtcacttacactaYcgctWtattgSHtSatcataaatataHt\ncgtYaaMNgBaatttaRgaMaatatttBtttaaaHHKaatctgatWatYaacttMctctt\nttVctagctDaaagtaVaKaKRtaacBgtatccaaccactHHaagaagaaggaNaaatBW\nattccgStaMSaMatBttgcatgRSacgttVVtaaDMtcSgVatWcaSatcttttVatag\nttactttacgatcaccNtaDVgSRcgVcgtgaacgaNtaNatatagtHtMgtHcMtagaa\nattBgtataRaaaacaYKgtRccYtatgaagtaataKgtaaMttgaaRVatgcagaKStc\ntHNaaatctBBtcttaYaBWHgtVtgacagcaRcataWctcaBcYacYgatDgtDHccta\n>THREE Homo sapiens frequency\naacacttcaccaggtatcgtgaaggctcaagattacccagagaacctttgcaatataaga\natatgtatgcagcattaccctaagtaattatattctttttctgactcaaagtgacaagcc\nctagtgtatattaaatcggtatatttgggaaattcctcaaactatcctaatcaggtagcc\natgaaagtgatcaaaaaagttcgtacttataccatacatgaattctggccaagtaaaaaa\ntagattgcgcaaaattcgtaccttaagtctctcgccaagatattaggatcctattactca\ntatcgtgtttttctttattgccgccatccccggagtatctcacccatccttctcttaaag\ngcctaatattacctatgcaaataaacatatattgttgaaaattgagaacctgatcgtgat\ntcttatgtgtaccatatgtatagtaatcacgcgactatatagtgctttagtatcgcccgt\ngggtgagtgaatattctgggctagcgtgagatagtttcttgtcctaatatttttcagatc\ngaatagcttctatttttgtgtttattgacatatgtcgaaactccttactcagtgaaagtc\natgaccagatccacgaacaatcttcggaatcagtctcgttttacggcggaatcttgagtc\ntaacttatatcccgtcgcttactttctaacaccccttatgtatttttaaaattacgttta\nttcgaacgtacttggcggaagcgttattttttgaagtaagttacattgggcagactcttg\nacattttcgatacgactttctttcatccatcacaggactcgttcgtattgatatcagaag\nctcgtgatgattagttgtcttctttaccaatactttgaggcctattctgcgaaatttttg\nttgccctgcgaacttcacataccaaggaacacctcgcaacatgccttcatatccatcgtt\ncattgtaattcttacacaatgaatcctaagtaattacatccctgcgtaaaagatggtagg\nggcactgaggatatattaccaagcatttagttatgagtaatcagcaatgtttcttgtatt\naagttctctaaaatagttacatcgtaatgttatctcgggttccgcgaataaacgagatag\nattcattatatatggccctaagcaaaaacctcctcgtattctgttggtaattagaatcac\nacaatacgggttgagatattaattatttgtagtacgaagagatataaaaagatgaacaat\ntactcaagtcaagatgtatacgggatttataataaaaatcgggtagagatctgctttgca\nattcagacgtgccactaaatcgtaatatgtcgcgttacatcagaaagggtaactattatt\naattaataaagggcttaatcactacatattagatcttatccgatagtcttatctattcgt\ntgtatttttaagcggttctaattcagtcattatatcagtgctccgagttctttattattg\nttttaaggatgacaaaatgcctcttgttataacgctgggagaagcagactaagagtcgga\ngcagttggtagaatgaggctgcaaaagacggtctcgacgaatggacagactttactaaac\ncaatgaaagacagaagtagagcaaagtctgaagtggtatcagcttaattatgacaaccct\ntaatacttccctttcgccgaatactggcgtggaaaggttttaaaagtcgaagtagttaga\nggcatctctcgctcataaataggtagactactcgcaatccaatgtgactatgtaatactg\nggaacatcagtccgcgatgcagcgtgtttatcaaccgtccccactcgcctggggagacat\ngagaccacccccgtggggattattagtccgcagtaatcgactcttgacaatccttttcga\nttatgtcatagcaatttacgacagttcagcgaagtgactactcggcgaaatggtattact\naaagcattcgaacccacatgaatgtgattcttggcaatttctaatccactaaagcttttc\ncgttgaatctggttgtagatatttatataagttcactaattaagatcacggtagtatatt\ngatagtgatgtctttgcaagaggttggccgaggaatttacggattctctattgatacaat\nttgtctggcttataactcttaaggctgaaccaggcgtttttagacgacttgatcagctgt\ntagaatggtttggactccctctttcatgtcagtaacatttcagccgttattgttacgata\ntgcttgaacaatattgatctaccacacacccatagtatattttataggtcatgctgttac\nctacgagcatggtattccacttcccattcaatgagtattcaacatcactagcctcagaga\ntgatgacccacctctaataacgtcacgttgcggccatgtgaaacctgaacttgagtagac\ngatatcaagcgctttaaattgcatataacatttgagggtaaagctaagcggatgctttat\nataatcaatactcaataataagatttgattgcattttagagttatgacacgacatagttc\nactaacgagttactattcccagatctagactgaagtactgatcgagacgatccttacgtc\ngatgatcgttagttatcgacttaggtcgggtctctagcggtattggtacttaaccggaca\nctatactaataacccatgatcaaagcataacagaatacagacgataatttcgccaacata\ntatgtacagaccccaagcatgagaagctcattgaaagctatcattgaagtcccgctcaca\natgtgtcttttccagacggtttaactggttcccgggagtcctggagtttcgacttacata\naatggaaacaatgtattttgctaatttatctatagcgtcatttggaccaatacagaatat\ntatgttgcctagtaatccactataacccgcaagtgctgatagaaaatttttagacgattt\nataaatgccccaagtatccctcccgtgaatcctccgttatactaattagtattcgttcat\nacgtataccgcgcatatatgaacatttggcgataaggcgcgtgaattgttacgtgacaga\ngatagcagtttcttgtgatatggttaacagacgtacatgaagggaaactttatatctata\ngtgatgcttccgtagaaataccgccactggtctgccaatgatgaagtatgtagctttagg\ntttgtactatgaggctttcgtttgtttgcagagtataacagttgcgagtgaaaaaccgac\ngaatttatactaatacgctttcactattggctacaaaatagggaagagtttcaatcatga\ngagggagtatatggatgctttgtagctaaaggtagaacgtatgtatatgctgccgttcat\ntcttgaaagatacataagcgataagttacgacaattataagcaacatccctaccttcgta\nacgatttcactgttactgcgcttgaaatacactatggggctattggcggagagaagcaga\ntcgcgccgagcatatacgagacctataatgttgatgatagagaaggcgtctgaattgata\ncatcgaagtacactttctttcgtagtatctctcgtcctctttctatctccggacacaaga\nattaagttatatatatagagtcttaccaatcatgttgaatcctgattctcagagttcttt\nggcgggccttgtgatgactgagaaacaatgcaatattgctccaaatttcctaagcaaatt\nctcggttatgttatgttatcagcaaagcgttacgttatgttatttaaatctggaatgacg\ngagcgaagttcttatgtcggtgtgggaataattcttttgaagacagcactccttaaataa\ntatcgctccgtgtttgtatttatcgaatgggtctgtaaccttgcacaagcaaatcggtgg\ntgtatatatcggataacaattaatacgatgttcatagtgacagtatactgatcgagtcct\nctaaagtcaattacctcacttaacaatctcattgatgttgtgtcattcccggtatcgccc\ngtagtatgtgctctgattgaccgagtgtgaaccaaggaacatctactaatgcctttgtta\nggtaagatctctctgaattccttcgtgccaacttaaaacattatcaaaatttcttctact\ntggattaactacttttacgagcatggcaaattcccctgtggaagacggttcattattatc\nggaaaccttatagaaattgcgtgttgactgaaattagatttttattgtaagagttgcatc\ntttgcgattcctctggtctagcttccaatgaacagtcctcccttctattcgacatcgggt\nccttcgtacatgtctttgcgatgtaataattaggttcggagtgtggccttaatgggtgca\nactaggaatacaacgcaaatttgctgacatgatagcaaatcggtatgccggcaccaaaac\ngtgctccttgcttagcttgtgaatgagactcagtagttaaataaatccatatctgcaatc\ngattccacaggtattgtccactatctttgaactactctaagagatacaagcttagctgag\naccgaggtgtatatgactacgctgatatctgtaaggtaccaatgcaggcaaagtatgcga\ngaagctaataccggctgtttccagctttataagattaaaatttggctgtcctggcggcct\ncagaattgttctatcgtaatcagttggttcattaattagctaagtacgaggtacaactta\ntctgtcccagaacagctccacaagtttttttacagccgaaacccctgtgtgaatcttaat\natccaagcgcgttatctgattagagtttacaactcagtattttatcagtacgttttgttt\nccaacattacccggtatgacaaaatgacgccacgtgtcgaataatggtctgaccaatgta\nggaagtgaaaagataaatat"

class ReverseComplementKotlin {
    private val map = ByteArray(128)

    init {
        val mappings = arrayOf(
            "ACBDGHK\nMNSRUTWVYacbdghkmnsrutwvy",
            "TGVHCDM\nKNSYAAWBRTGVHCDMKNSYAAWBR"
        )
        for (i in mappings[0].indices) {
            map[mappings[0][i].toInt()] = mappings[1][i].toByte()
        }
    }

    private fun reverse(
        byteArray: ByteArray,
        beginningIndex: Int,
        endIndex: Int
    ) {
        var beginningIndex = beginningIndex
        var endIndex = endIndex
        while (true) {
            var byteBeginning = byteArray[beginningIndex]
            if (byteBeginning == '\n'.toByte()) {
                byteBeginning = byteArray[++beginningIndex]
            }
            var byteEnding = byteArray[endIndex]
            if (byteEnding == '\n'.toByte()) {
                byteEnding = byteArray[--endIndex]
            }
            if (beginningIndex > endIndex) {
                break
            }
            byteArray[beginningIndex++] = map[byteEnding.toInt()]
            byteArray[endIndex--] = map[byteBeginning.toInt()]
        }
    }

    fun benchmark(): String {
        val buf = FASTA_INPUT.toByteArray()
        var i = 0
        while (i < buf.size) {
            while (buf[i++] != '\n'.toByte()) {
            }
            val startIndex = i
            while (i < buf.size && buf[i++] != '>'.toByte()) {
            }
            reverse(buf, startIndex, i - 2)
        }
        return String(buf)
    }
}